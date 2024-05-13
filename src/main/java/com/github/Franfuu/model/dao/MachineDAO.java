package com.github.Franfuu.model.dao;

import com.github.Franfuu.model.connection.ConnectionMariaDB;
import com.github.Franfuu.model.entity.Machine;

import java.io.IOException;
import java.sql.*;

public class MachineDAO implements DAO<Machine, Integer> {
    private final static String INSERT = "INSERT INTO machine (MachineCode, RoomCode, MachineType) VALUES (?,?,?)";
    private final static String UPDATE = "UPDATE machine SET RoomCode=?, MachineType=? WHERE MachineCode=?";
    private final static String FINDBYCODE = "SELECT MachineCode, RoomCode, MachineType FROM machine WHERE MachineCode=?";
    private final static String DELETE = "DELETE FROM machine WHERE MachineCode=?";

    private static Connection conn;

    private MachineDAO() {
        conn = ConnectionMariaDB.getConnection();
    }


    public static Machine save(Machine entity) throws SQLException {
        Machine result = entity;
        if (entity == null || entity.getCode() == 0) return result;
        try {
            if (findByMachineCode(entity.getCode()) == null) {
                // INSERT
                try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    pst.setInt(1, entity.getCode());
                    pst.setInt(2, entity.getRoom().getCode());
                    pst.setString(3, entity.getMachineType());
                    pst.executeUpdate();
                }
            } else {
                // UPDATE
                try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
                    pst.setInt(1, entity.getRoom().getCode());
                    pst.setString(2, entity.getMachineType().toString());
                    pst.setInt(3, entity.getCode());
                    pst.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


            public Machine Machine(Machine entity) {
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


        public static Machine findByMachineCode (Integer code) throws SQLException {
            Machine result = null;
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYCODE)) {
                pst.setInt(1, code);
                try (ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        result.setCode(res.getInt("MachineCode"));
                        result.getRoom().setCode(res.getInt("RoomCode"));
                        result.setMachineType(res.getString("MachineType"));
                    }
                    res.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }


        public void close () throws IOException {

        }

        public static MachineDAO build () {
            return new MachineDAO();
        }


    }
