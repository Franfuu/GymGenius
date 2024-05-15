package com.github.Franfuu.view;

import com.github.Franfuu.App;
import com.github.Franfuu.model.dao.ClientDAO;
import com.github.Franfuu.model.dao.MachineDAO;
import com.github.Franfuu.model.dao.RoomDAO;
import com.github.Franfuu.model.entity.Client;
import com.github.Franfuu.model.entity.Machine;
import com.github.Franfuu.model.entity.Room;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;


public class AddMachineController extends Controller implements Initializable {
    @FXML
    private Button addMachineButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldRoom;

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
        String machineType = fieldName.getText();
        String roomCodeText = fieldRoom.getText();

        if (machineType.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "El campo del tipo de máquina no puede estar vacío.");
            return;
        }

        if (roomCodeText.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "El campo del código de habitación no puede estar vacío.");
            return;
        }

        int roomCode;
        try {
            roomCode = Integer.parseInt(roomCodeText);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "El código de habitación ingresado no es válido.");
            return;
        }

        RoomDAO rdao = new RoomDAO();
        Room room = rdao.findByRoomCode(roomCode);
        if (room == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "El código de habitación no se encuentra en la base de datos.");
            return;
        }


        Machine machine = new Machine(room, machineType);
        MachineDAO cdao = new MachineDAO();
        cdao.save(machine);

        if (room == null) {
            rdao.save(machine.getRoom());
        }

        App.currentController.changeScene(Scenes.SHOWMACHINES, null);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
