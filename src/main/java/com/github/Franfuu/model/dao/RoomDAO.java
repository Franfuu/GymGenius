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
    private final static String INSERT = "INSERT INTO room (RoomCode) VALUES (?)";
    private final static String UPDATE = "UPDATE room SET RoomCode=? WHERE RoomCode=?";
    private final static String FINDALL = "SELECT RoomCode FROM room";
    private final static String FINDBYCODE = "SELECT RoomCode FROM room WHERE RoomCode=?";
    private final static String DELETE = "DELETE FROM room WHERE RoomCode=?";


    public static Room save(Room entity) {
        Room result = entity;
        if (entity == null || entity.getCode() == 0) return result;
        try {
            if (findByRoomCode(entity.getCode()) == null) {
                // INSERT
                try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    pst.setInt(1, entity.getCode());
                    pst.executeUpdate();
                }
            } else {
                // UPDATE
                try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
                    pst.setInt(1, entity.getCode());
                    pst.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

        public static Room delete(Room entity) throws SQLException {
            if (entity == null || entity.getCode() == 0) return entity;
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
                pst.setInt(1, entity.getCode());
                pst.executeUpdate();
            }
            return entity;
        }

        private static Room findByRoomCode(Integer code) {
            Room result = new Room();
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYCODE)) {
                pst.setInt(1, code);
                ResultSet res = pst.executeQuery();
                if (res.next()) {
                    result.setCode(res.getInt("RoomCode"));
                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }

        public static List<Room> findAll() {
            List<Room> result = new ArrayList<>();
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDALL)) {
                ResultSet res = pst.executeQuery();
                while (res.next()) {
                    Room room = new Room();
                    room.setCode(res.getInt("RoomCode"));
                    result.add(room);
                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }

        public void close() throws IOException {
            // Aquí puedes agregar código para cerrar recursos si es necesario
        }

        public static RoomDAO build() {
            return new RoomDAO();
        }




}


