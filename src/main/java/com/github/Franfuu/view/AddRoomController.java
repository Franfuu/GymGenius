package com.github.Franfuu.view;

import com.github.Franfuu.App;
import com.github.Franfuu.model.dao.MachineDAO;
import com.github.Franfuu.model.dao.RoomDAO;
import com.github.Franfuu.model.entity.Machine;
import com.github.Franfuu.model.entity.Room;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddRoomController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField fieldRoom;
    @FXML
    private Button addRoomButton;

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void onSave(Event event) throws Exception {
        String roomCodeText = fieldRoom.getText().trim();

        if (roomCodeText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "El campo no puede estar vacío.");
        } else {
            try {
                int roomCode = Integer.parseInt(roomCodeText);


                if (roomCode > 9999) {
                    showAlert(Alert.AlertType.ERROR, "Error", "El valor no puede ser mayor que 9999.");
                } else {
                    RoomDAO roomDAO = new RoomDAO();


                    if (roomDAO.findByRoomCode(roomCode) != null) {
                        showAlert(Alert.AlertType.ERROR, "Error", "El código de habitación ya existe en la base de datos.");
                    } else {
                        // Guardar la habitación
                        Room room = new Room(roomCode);
                        roomDAO.save(room);


                        App.currentController.changeScene(Scenes.SHOWMACHINES, null);
                        ((Node) (event.getSource())).getScene().getWindow().hide();
                    }
                }
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "El valor ingresado no es válido.");
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
