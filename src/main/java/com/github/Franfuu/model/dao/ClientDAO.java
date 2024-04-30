package com.github.Franfuu.model.dao;


import com.github.Franfuu.model.connection.ConnectionMariaDB;
import com.github.Franfuu.model.entity.Client;

import java.io.IOException;
import java.sql.*;


public class ClientDAO implements DAO<Client, Integer> {
    private static final String FINDBYCODE = "SELECT c.ClientCode, c.Name, c.Surname, c.Dni, c.Phone FROM client AS c WHERE c.ClientCode=?";
    private static final String INSERT = "INSERT INTO client (ClientCode, Name, Surname, Dni, Phone, Sex) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE client SET Name=?, Surname=?, Dni=?, Phone=? WHERE ClientCode=?";
    private static final String DELETE = "DELETE FROM client WHERE ClientCode=?";



    private static Connection conn;

    public ClientDAO() {
        conn = ConnectionMariaDB.getConnection();
    }

    public static Client save(Client entity) throws SQLException {
        Client result = entity;
        if (conn == null) {
            conn = ConnectionMariaDB.getConnection();
        }
        if (entity != null) {
            int clientCode = entity.getClientCode();
            if (clientCode != 0) {
                Client isInDataBase = findByCode(clientCode);
                if (isInDataBase == null) {
                    // INSERT
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                        pst.setInt(1, entity.getClientCode());
                        pst.setString(2, entity.getName());
                        pst.setString(3, entity.getSurname());
                        pst.setString(4, entity.getDni());
                        pst.setString(5, entity.getPhone());
                        pst.setString(6, entity.getSex());
                        pst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    // UPDATE
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
                        pst.setString(1, entity.getName());
                        pst.setString(2, entity.getSurname());
                        pst.setString(3, entity.getDni());
                        pst.setString(4, entity.getPhone());
                        pst.setString(5, entity.getSex());
                        pst.setInt(6, entity.getClientCode());
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
        if (entity != null) {
            try (PreparedStatement pst = conn.prepareStatement(DELETE)) {
                pst.setInt(1, entity.getClientCode());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                entity = null;
            }
        }
        return entity;
    }

    public static Client findByCode(Integer clientCode) throws SQLException {
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
                            res.getString("Sex")
                    );
                }
            }
        }


        return result;
    }


    public void close() throws IOException {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ClientDAO build() {
        return new ClientDAO();
    }


}
