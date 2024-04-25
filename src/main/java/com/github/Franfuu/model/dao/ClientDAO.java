package com.github.Franfuu.model.dao;


import com.github.Franfuu.model.connection.ConnectionMariaDB;
import com.github.Franfuu.model.entity.Client;
import com.github.Franfuu.model.entity.Sex;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
//dejame pushear


public class ClientDAO implements DAO<Client, Integer> {
    private static final String FINDBYCODE ="SELECT c.ClientCode, c.Name, c.Surname, c.Dni, c.Phone FROM client AS c WHERE c.ClientCode=?";
    private static final String INSERT ="INSERT INTO client (ClientCode, Name, Surname, Dni, Phone) VALUES (?,?,?,?,?)";
    private static final String UPDATE ="UPDATE client SET Name=?, Surname=?, Dni=?, Phone=? WHERE ClientCode=?";
    private static final String DELETE ="DELETE FROM client WHERE ClientCode=?";

    private Connection conn;

    public ClientDAO(){
        conn = ConnectionMariaDB.getConnection();
    }

    public Client save(Client entity) {
        Client result = entity;
        if(entity != null) {
            int clientCode = entity.getClientCode();
            if(clientCode != 0) {
                Client isInDataBase = findByCode(clientCode);
                if(isInDataBase == null) {
                    // INSERT
                    try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                        pst.setInt(1, entity.getClientCode());
                        pst.setString(2, entity.getName());
                        pst.setString(3, entity.getSurname());
                        pst.setString(4, entity.getDni());
                        pst.setString(5, entity.getPhone());
                        pst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    // UPDATE
                    try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
                        pst.setString(1, entity.getName());
                        pst.setString(2, entity.getSurname());
                        pst.setString(3, entity.getDni());
                        pst.setString(4, entity.getPhone());
                        pst.setInt(5, entity.getClientCode());
                        pst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }


    public Client delete(Client entity) {
        if(entity != null) {
            try(PreparedStatement pst = conn.prepareStatement(DELETE)) {
                pst.setInt(1, entity.getClientCode());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                entity = null;
            }
        }
        return entity;
    }

    public Client findByCode(Integer clientCode) {
        Client result = null;
        try (PreparedStatement pst = conn.prepareStatement(FINDBYCODE)) {
            pst.setInt(1, clientCode);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    result = new Client(
                            res.getInt("ClientCode"),
                            res.getString("Name"),
                            res.getString("Surname"),
                            res.getString("Dni"),
                            res.getString("Phone"),
                            null, // Deberás manejar la foto por separado
                            null  // Deberás manejar el sexo por separado
                    );

                    // Manejar la foto por separado
                    byte[] photoBytes = res.getBytes("Photo");
                    if (photoBytes != null) {
                        ByteArrayInputStream bis = new ByteArrayInputStream(photoBytes);
                        Image image = new Image(bis);
                        result.setPhoto(image);
                    }

                    // Manejar el sexo por separado
                    String sexStr = res.getString("Sex");
                    if (sexStr != null) {
                        Sex sex = Sex.valueOf(sexStr);
                        result.setSex(sex);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void close() throws IOException {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ClientDAO build(){
        return new ClientDAO();
    }


}
