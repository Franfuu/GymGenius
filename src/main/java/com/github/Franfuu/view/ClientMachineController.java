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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    private ObservableList<Machine> machineList;

    @Override
    public void onOpen(Object input) throws Exception {
        List<Machine> machines = MachineDAO.findAll();
        System.out.println(machines);
        this.machineList = FXCollections.observableArrayList(machines);
        tableMachine.setItems(this.machineList);
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colCodeMachine.setCellValueFactory(machine -> new SimpleIntegerProperty(machine.getValue().getCode()).asObject());

        colRoom.setCellValueFactory(machine -> new SimpleIntegerProperty(machine.getValue().getRoom().getCode()).asObject());
        /*colRoom.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colRoom.setOnEditCommit(event -> {
            try {
                if (event.getNewValue().equals(event.getOldValue())) {
                    return;
                }
                Machine machine = MachineDAO.findByMachineCode(event.getRowValue().getCode());
                Room room = machine.getRoom();
                if (event.getNewValue().longValue() <= 60) {
                    room.setCode(Integer.parseInt(String.valueOf(event.getNewValue())));
                    MachineDAO.build().update(machine);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error, el campo no puede superar los 50 caracteres");
                    alert.show();
                }
            } catch (NumberFormatException | SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error, el valor ingresado no es un número válido");
                alert.show();
            }
        });*/

        colMachine.setCellValueFactory(machine -> new SimpleStringProperty(machine.getValue().getMachineType()));
        /*colMachine.setCellFactory(TextFieldTableCell.forTableColumn());
        colMachine.setOnEditCommit(event -> {
            try {
                if (event.getNewValue().equals(event.getOldValue())) {
                    return;
                }

                if (event.getNewValue().length() <= 60) {
                    Machine machine = event.getRowValue();
                    machine.setMachineType(event.getNewValue());
                    MachineDAO.build().update(machine);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error, el campo no puede superar los 50 caracteres");
                    alert.show();
                }
            } catch (NumberFormatException | SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error, el valor ingresado no es un número válido");
                alert.show();
            }
        });*/
    }

}
