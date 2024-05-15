package com.github.Franfuu.model.dao;

import com.github.Franfuu.model.connection.ConnectionMariaDB;
import com.github.Franfuu.model.entity.Client;
import com.github.Franfuu.model.entity.Machine;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private static final String FINDALL = "SELECT * FROM client";
    private static final String FINDBYCODE = "SELECT * FROM client WHERE Clientcode=?";
    private static final String INSERT = "INSERT INTO client ( Name, Surname, Email, Password, DNI, Sex) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE client SET Name=?, Surname=?, Email=?, Password=?, DNI=?, Sex=? WHERE ClientCode=?";
    private static final String DELETE = "DELETE FROM client WHERE ClientCode=?";
    private static final String FINDBYEMAIL ="SELECT * FROM client WHERE Email=?";
    private static final String INSERTNM = "INSERT INTO client_machine (ClientCode, MachineCode) VALUES (?, ?)";
    private static final String DELETENM = "DELETE FROM client_machine WHERE ClientCode=?";

    private static Connection conn;

    public ClientDAO() {
        conn = ConnectionMariaDB.getConnection();
    }

    public Client save(Client entity) {
        Client result = new Client();
        if (entity == null || entity.getCode() != 0) return result;
        try {
            if (findByClientCode(entity.getCode()) == null) {
                // INSERT CLIENT
                try (PreparedStatement pst = conn.prepareStatement(INSERT)) {
                    pst.setString(1, entity.getName());
                    pst.setString(2, entity.getSurname());
                    pst.setString(3, entity.getEmail());
                    pst.setString(4, entity.getPassword());
                    pst.setString(5, entity.getDni());
                    pst.setString(6, entity.getSex());

                    pst.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public Client update(Client entity) {
        Client result = new Client();
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
            pst.setString(1, entity.getName());
            pst.setString(2, entity.getSurname());
            pst.setString(3, entity.getEmail());
            pst.setString(4, entity.getPassword());
            pst.setString(5, entity.getDni());
            pst.setString(6, entity.getSex());
            pst.setString(7, String.valueOf(entity.getCode()));
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ClientDAO build() {
        return new ClientDAO();
    }


    public Client delete(Client entity) {
        if (entity != null) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
                pst.setInt(1, entity.getCode());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                entity = null;
            }
        }
        return entity;
    }


    public static Client findByClientCode(Integer code) {
        Client result = null;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYCODE)) {
            pst.setInt(1, code);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    Client c = new ClientLazy();
                    c.setCode(res.getInt("ClientCode"));
                    c.setName(res.getString("Name"));
                    c.setSurname(res.getString("Surname"));
                    c.setEmail(res.getString("Email"));
                    c.setPassword(res.getString("Password"));
                    c.setDni(res.getString("DNI"));
                    c.setSex(res.getString("Sex"));
                    result = c;
                }
                res.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public List<Client> findAll() {
        List<Client> result = new ArrayList<>();
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDALL)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Client c = new ClientLazy();
                    c.setCode(res.getInt("ClientCode"));
                    c.setName(res.getString("Name"));
                    c.setSurname(res.getString("Surname"));
                    c.setEmail(res.getString("Email"));
                    c.setPassword(res.getString("Password"));
                    c.setDni(res.getString("DNI"));
                    c.setSex(res.getString("Sex"));
                    result.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static Client findByEmail(String email) {
        Client result = null;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYEMAIL)) {
            pst.setString(4, email);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    Client c = new ClientLazy();
                    c.setEmail(res.getString("Email"));
                    result = c;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void insertMachineFromClient(int clientCode, int machineCode) throws SQLException {
        if (clientCode != 0 || machineCode != 0) return;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERTNM)) {
            pst.setInt(1, clientCode);
            pst.setInt(2, machineCode);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMachineFromClient(int clientCode) throws SQLException {

        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETENM)) {
            pst.setInt(1, clientCode);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void close() throws IOException {

    }
}

class ClientLazy extends Client {
    private static final String FINDMACHINESBYCLIENTS = "INSERT INTO ClientMachine (ClientCode, MachineCode) VALUES (?, ?)";

    public ClientLazy() {

    }

    public ClientLazy( String name, String surname, String email, String password, String dni, String sex) {
        super( name, surname, email, password, dni, sex);
    }

    @Override
    public List<Machine> getMachines() {
        if (super.getMachines() == null) {
            Connection connection = ConnectionMariaDB.getConnection();
            List<Machine> result = new ArrayList<>();
            try (PreparedStatement pst = connection.prepareStatement(FINDMACHINESBYCLIENTS)) {
                pst.setInt(1, getCode());
                try (ResultSet res = pst.executeQuery()) {
                    while (res.next()) {
                        Client c = new Client();
                        Machine m = new Machine();
                        c.setCode(res.getInt("ClientCode"));
                        m.setCode(res.getInt("MachineCode"));
                        result.add(c);
                    }
                    res.close();
                }
                super.setMachines(result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return super.getMachines();
    }
}


