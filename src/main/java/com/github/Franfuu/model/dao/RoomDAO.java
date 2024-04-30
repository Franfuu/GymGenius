package com.github.Franfuu.model.dao;

import com.github.Franfuu.model.connection.ConnectionMariaDB;
import com.github.Franfuu.model.entity.Room;
import com.github.Franfuu.model.entity.Machine;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO implements DAO<Room, Integer> {
    private final static String INSERT = "INSERT INTO room (RoomCode, NRoom) VALUES (?,?)";
    private final static String UPDATE = "UPDATE room SET NRoom=? WHERE RoomCode=?";
    private final static String FINDALL = "SELECT RoomCode, NRoom FROM room";
    private final static String FINDBYROOMCODE = "SELECT RoomCode, NRoom FROM room WHERE RoomCode=?";
    private final static String DELETE = "DELETE FROM room WHERE RoomCode=?";


    public Room save(Room entity) {
        Room result = entity;
        if (entity == null || entity.getRoomCode() == 0) return result;
        Room roomInDatabase = findByCode(entity.getRoomCode());
        if (roomInDatabase == null) {
            // INSERT
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                pst.setInt(1, entity.getRoomCode());
                pst.setInt(2, entity.getNRoom());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // UPDATE
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
                pst.setInt(1, entity.getNRoom());
                pst.setInt(2, entity.getRoomCode());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public Room delete(Room entity) throws SQLException {
        if (entity == null || entity.getRoomCode() == 0) return entity;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
            pst.setInt(1, entity.getRoomCode());
            pst.executeUpdate();
        }
        return entity;
    }


    public Room findByCode(Integer key) {
        Room result = new Room();
        if (key == 0) return result;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYROOMCODE)) {
            pst.setInt(1, key);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                result.setRoomCode(res.getInt("RoomCode"));
                result.setNRooms(res.getInt("NRooms"));
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Room> findAll() {
        List<Room> result = new ArrayList<>();
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDALL)) {
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Room room = new Room();
                room.setRoomCode(res.getInt("RoomCode"));
                room.setNRooms(res.getInt("NRooms"));
                result.add(room);
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

    public static RoomDAO build() {
        return new RoomDAO();
    }
}
