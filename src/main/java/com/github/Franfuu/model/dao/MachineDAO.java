package com.github.Franfuu.model.dao;

import com.github.Franfuu.model.connection.ConnectionMariaDB;
import com.github.Franfuu.model.entity.Machine;
import com.github.Franfuu.model.entity.MachineType;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MachineDAO implements DAO<Machine, Integer> {
    private final static String INSERT = "INSERT INTO machine (MachineCode, RoomCode, MachineType) VALUES (?,?,?)";
    private final static String UPDATE = "UPDATE machine SET RoomCode=?, MachineType=? WHERE MachineCode=?";
    private final static String FINDBYCODE = "SELECT MachineCode, RoomCode, MachineType FROM machine WHERE MachineCode=?";
    private final static String DELETE = "DELETE FROM machine WHERE MachineCode=?";

    @Override
    public Machine save(Machine entity) {
        Machine result = entity;
        if (entity == null || entity.getMachineCode() == 0) return result;
        Machine machineInDatabase = findByCode(entity.getMachineCode());
        if (machineInDatabase == null) {
            // INSERT
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                pst.setInt(1, entity.getMachineCode());
                pst.setInt(2, entity.getRoomCode());
                pst.setString(3, entity.getMachineType().toString());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // UPDATE
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
                pst.setInt(1, entity.getRoomCode());
                pst.setString(2, entity.getMachineType().toString());
                pst.setInt(3, entity.getMachineCode());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Machine delete(Machine entity) throws SQLException {
        if (entity == null || entity.getMachineCode() == 0) return entity;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
            pst.setInt(1, entity.getMachineCode());
            pst.executeUpdate();
        }
        return entity;
    }

    @Override
    public Machine findByCode(Integer key) {
        Machine result = new Machine();
        if (key == 0) return result;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYCODE)) {
            pst.setInt(1, key);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                result.setMachineCode(res.getInt("MachineCode"));
                result.setRoomCode(res.getInt("RoomCode"));
                result.setMachineType(MachineType.valueOf(res.getString("MachineType")));
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

    public static MachineDAO build() {
        return new MachineDAO();
    }
}
