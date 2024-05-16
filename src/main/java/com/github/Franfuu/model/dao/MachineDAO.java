package com.github.Franfuu.model.dao;

import com.github.Franfuu.model.connection.ConnectionMariaDB;
import com.github.Franfuu.model.entity.Machine;
import com.github.Franfuu.model.entity.Room;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MachineDAO implements DAO<Machine, Integer> {
    private static final String INSERT = "INSERT INTO Machine (RoomCode, MachineType) VALUES (?,?)";
    private static final String UPDATE = "UPDATE Machine SET RoomCode=?, MachineType=? WHERE MachineCode=?";
    private static final String FIND_BY_CODE = "SELECT MachineCode, RoomCode, MachineType FROM Machine WHERE MachineCode=?";
    private static final String DELETE = "DELETE FROM Machine WHERE MachineCode=?";
    private static final String FINDALL = "SELECT * FROM Machine";

    public MachineDAO() {
    }

    public  Machine save(Machine entity) throws SQLException {
        if (entity == null) return null;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, entity.getRoom().getCode());
            pst.setString(2, entity.getMachineType());
            pst.executeUpdate();
            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setCode(generatedKeys.getInt(1));
                }
            }
        }
        return entity;
    }

    public Machine update(Machine entity) throws SQLException {
        if (entity == null || entity.getRoom() == null) return null;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
            pst.setInt(1, entity.getRoom().getCode());
            pst.setString(2, entity.getMachineType());
            pst.setInt(3, entity.getCode());
            pst.executeUpdate();
        }
        return entity;
    }


    public Machine delete(Machine entity) throws SQLException {
        if (entity == null) return null;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
            pst.setInt(1, entity.getCode());
            pst.executeUpdate();
        }
        return entity;
    }

    public static Machine findByMachineCode(Integer code) throws SQLException {
        Machine result = null;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FIND_BY_CODE)) {
            pst.setInt(1, code);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    result = new Machine();
                    result.setCode(res.getInt("MachineCode"));
                    result.setRoom(RoomDAO.findByRoomCode(res.getInt("RoomCode")));
                    result.setMachineType(res.getString("MachineType"));
                }
            }
        }
        return result;
    }

    public static List<Machine> findAll() throws SQLException {
        List<Machine> result = new ArrayList<>();
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDALL)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Machine machine = new Machine();
                    machine.setCode(res.getInt("MachineCode"));
                    //Eager
                    machine.setRoom(RoomDAO.findByRoomCode(res.getInt("RoomCode")));
                    machine.setMachineType(res.getString("MachineType"));
                    result.add(machine);
                }
            }
        }
        return result;

    }
    public void close() throws IOException {
        // Not implemented for now
    }

    public static MachineDAO build() {
        return new MachineDAO();
    }
}
