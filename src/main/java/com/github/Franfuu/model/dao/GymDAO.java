package com.github.Franfuu.model.dao;

import com.github.Franfuu.model.connection.ConnectionMariaDB;
import com.github.Franfuu.model.entity.Client;
import com.github.Franfuu.model.entity.Gym;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GymDAO implements DAO<Gym, Integer> {
    private final static String INSERT = "INSERT INTO gym (GymCode, Name, PostalCode, Address) VALUES (?,?,?,?)";
    private final static String UPDATE = "UPDATE gym SET Name=?, PostalCode=?, Address=? WHERE GymCode=?";
    private final static String FINDBYCODE = "SELECT GymCode, Name, PostalCode, Address FROM gym WHERE GymCode=?";

    private final static String FINDBYDNI="SELECT a.dni,a.name FROM author AS a WHERE a.dni=?";
    private final static String DELETE = "DELETE FROM gym WHERE GymCode=?";

    @Override
    public Gym save(Gym entity) {
        Gym result = entity;
        if (entity == null || entity.getGymCode() == 0) return result;
        Gym gymInDatabase = findByCode(entity.getGymCode());
        if (gymInDatabase == null) {
            // INSERT
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                pst.setInt(1, entity.getGymCode());
                pst.setString(2, entity.getName());
                pst.setInt(3, entity.getPostalCode());
                pst.setString(4, entity.getAdress());
                pst.executeUpdate();

                //save cascade -> opcional
                if(entity.getClients()!=null) {
                    for (Client c : entity.getClients()) {
                        ClientDAO.build().save(c);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // UPDATE
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
                pst.setString(1, entity.getName());
                pst.setInt(2, entity.getPostalCode());
                pst.setString(3, entity.getAdress());
                pst.setInt(4, entity.getGymCode());
                pst.executeUpdate();

                /*if(entity.getClients()!=null){
                    List<Client> booksBefore = ClientDAO.build().findByCode(entity.getClients());
                    List<Client> booksAfter = entity.getClients();

                    List<Client> booksToBeRemoved = new ArrayList<>(clientsBefore);
                    booksToBeRemoved.removeAll(booksAfter);

                    for(Client b:booksToBeRemoved){
                        ClientDAO.build().delete(b);
                    }
                    for(Client b:booksAfter){
                        ClientDAO.build().save(b);
                    }
                }*/

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Gym delete(Gym entity) throws SQLException {
        if (entity == null || entity.getGymCode() == 0) return entity;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
            pst.setInt(1, entity.getGymCode());
            pst.executeUpdate();
        }
        return entity;
    }

    @Override
    public Gym findByCode(Integer key) {
        Gym result = new Gym();
        if (key == 0) return result;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYCODE)) {
            pst.setInt(1, key);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                result.setGymCode(res.getInt("GymCode"));
                result.setName(res.getString("Name"));
                result.setPostalCode(res.getInt("PostalCode"));
                result.setAdress(res.getString("Address"));
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void close() throws IOException {

    }

    public static GymDAO build() {
        return new GymDAO();
    }
}
