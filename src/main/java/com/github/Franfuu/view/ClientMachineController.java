package com.github.Franfuu.view;

import com.github.Franfuu.model.dao.ClientDAO;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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
    private ObservableList<Machine> machineList;

    @Override
    public void onOpen(Object input) throws Exception {
/*
        machineList = FXCollections.observableArrayList();
        List<Machine> list = new MachineDAO().getMachinesByClient((Client) input);
        for (Machine machine : list) {
            machineList.add(machine);
        }
        tableMachine.setItems(machineList);*/
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colMachine.setCellValueFactory(machine -> new SimpleStringProperty(machine.getValue().getMachineType()));
        colCodeMachine.setCellValueFactory(machine -> new SimpleIntegerProperty(machine.getValue().getCode()).asObject());
        colRoom.setCellValueFactory(machine -> new SimpleIntegerProperty(machine.getValue().getRoom().getCode()).asObject());
    }

}
