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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
        int roomCode = Integer.parseInt(fieldRoom.getText());

        RoomDAO rdao = new RoomDAO();
        Room room = rdao.findByRoomCode(roomCode);
        Machine machine = new Machine(room, machineType);
        MachineDAO cdao = new MachineDAO();
        cdao.save(machine);

        if (room == null) { rdao.save(machine.getRoom()); }
        App.currentController.changeScene(Scenes.MAINPAGE, null);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
