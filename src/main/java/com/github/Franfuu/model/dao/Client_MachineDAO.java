package com.github.Franfuu.model.dao;

import com.github.Franfuu.model.connection.ConnectionMariaDB;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

public class Client_MachineDAO {
    private static final String INSERTCM = "INSERT INTO client_machine (ClientCode, MachineCode) VALUES (?, ?)";
    private static final String DELETEMC = "DELETE FROM client_machine WHERE ClientCode = ? AND MachineCode = ?;";



    public Client_MachineDAO() {

    }
    public static Client_MachineDAO build() {
        return new Client_MachineDAO();
    }

    public static void insertMachineToClient(int machineCode, int clientCode){


        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERTCM)) {
            pst.setInt(1, machineCode);
            pst.setInt(2,clientCode );
            pst.executeUpdate();
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000") && e.getErrorCode() == 1062) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error: Entrada duplicada encontrada.");
                alert.show();
            } else {
                e.printStackTrace();
            }
        }
    }
    public static boolean deleteMachineFromClient(int clientCode, int machineCode) throws SQLException {

        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETEMC)) {
            pst.setInt(1, clientCode);
            pst.setInt(2, machineCode);
            int rowsAffected = pst.executeUpdate();

            return rowsAffected > 0;
        }
    }



}
