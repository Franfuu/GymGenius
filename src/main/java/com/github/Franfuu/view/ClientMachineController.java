package com.github.Franfuu.view;

import com.github.Franfuu.App;
import com.github.Franfuu.model.dao.ClientDAO;
import com.github.Franfuu.model.dao.Client_MachineDAO;
import com.github.Franfuu.model.dao.MachineDAO;
import com.github.Franfuu.model.entity.Client;
import com.github.Franfuu.model.entity.Machine;
import com.github.Franfuu.model.entity.Room;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ClientMachineController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView exitButton;
    @FXML
    private TableView<Machine> tableMachine;
    @FXML
    private TableColumn<Machine, String> colMachine;
    @FXML
    private TableColumn<Machine, Integer> colCodeMachine;
    @FXML
    private TableColumn<Machine, Integer> colRoom;
    @FXML
    private Text textName;
    private ObservableList<Machine> machineList;
    private Client client;

    @Override
    public void onOpen(Object input) throws Exception {
        client = (Client) input;
        showName();
        List<Machine> machines = MachineDAO.findByCode(client.getCode());
        this.machineList = FXCollections.observableArrayList(machines);
        tableMachine.setItems(this.machineList);
    }



    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCodeMachine.setCellValueFactory(machine -> new SimpleIntegerProperty(machine.getValue().getCode()).asObject());
        colMachine.setCellValueFactory(machine -> new SimpleStringProperty(machine.getValue().getMachineType()));
        colRoom.setCellValueFactory(machine -> new SimpleIntegerProperty(machine.getValue().getRoom().getCode()).asObject());
    }
    private void showName() {
        textName.setText(client.getName() + " " + client.getSurname());
    }
    @FXML
    private void goBack() throws Exception {
        App.currentController.changeScene(Scenes.MAINPAGE, null);
    }
}
