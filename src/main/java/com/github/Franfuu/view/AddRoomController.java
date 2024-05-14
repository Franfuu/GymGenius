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
        int roomCode = Integer.parseInt(fieldRoom.getText());

        // Crear una instancia de Room con el código proporcionado
        Room room = new Room(roomCode);

        // Crear una instancia de RoomDAO
        RoomDAO roomDAO = new RoomDAO();

        // Guardar la habitación en la base de datos
        roomDAO.save(room);
        App.currentController.changeScene(Scenes.MAINPAGE, null);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
