package com.github.Franfuu.view;

import com.github.Franfuu.App;
import com.github.Franfuu.model.dao.MachineDAO;
import com.github.Franfuu.model.dao.RoomDAO;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteRoomController extends Controller implements Initializable {
    @FXML
    private Button deleteRoomButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField fieldRoomCode;
    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    /**
     * Handles the action of deleting a room from the database.
     * Retrieves the room code from the text field, validates it,
     * and deletes the room if valid. Shows appropriate alert messages
     * for success, validation errors, or database operation failures.
     *
     * @param event the event triggered by the delete room button
     */
    @FXML
    private void deleteRoom(Event event) {
        String roomCode = fieldRoomCode.getText().trim();

        if (roomCode.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "El campo del código de sala no puede estar vacío.");
        } else {
            try {
                if (!roomCode.matches("\\d+")) {
                    showAlert(Alert.AlertType.ERROR, "Error", "El código de sala debe contener solo números.");
                    return;
                }

                boolean deleted = RoomDAO.delete(Integer.parseInt(roomCode));

                if (deleted) {
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "La sala se ha eliminada correctamente.");
                    App.currentController.changeScene(Scenes.SHOWMACHINES, null);
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "El código de sala no coincide con ninguna sala en la base de datos.");
                }
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "El código de sala debe ser un número válido.");
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Error al eliminar la sala de la base de datos.");
                e.printStackTrace();
            }
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
