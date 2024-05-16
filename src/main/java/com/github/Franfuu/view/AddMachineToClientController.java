package com.github.Franfuu.view;

import com.github.Franfuu.App;
import com.github.Franfuu.model.dao.ClientDAO;
import com.github.Franfuu.model.dao.Client_MachineDAO;
import com.github.Franfuu.model.dao.MachineDAO;
import com.github.Franfuu.model.entity.Client;
import com.github.Franfuu.model.entity.Machine;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class AddMachineToClientController extends Controller implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button addMachineToClientButton;
    @FXML
    private ComboBox<String> machineComboBox;
    @FXML
    private ComboBox<String> clientComboBox;
    @FXML
    private ImageView goBackButton;

    private  Map<String, String> machinesMap;
    private  Map<String, String> clientsMap;

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    public void goBackButton() throws Exception {
        App.currentController.changeScene(Scenes.MAINPAGE, null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Client> clients = ClientDAO.build().findAll();
        //List<String> ClientCode = clients.stream().map(client -> String.valueOf(client.getCode())).collect(Collectors.toList());
        clientsMap = new HashMap<>();
        for (Client client : clients) {
            clientsMap.put(String.valueOf(client.getCode()), client.getName());
        }
        clientComboBox.setItems(FXCollections.observableArrayList(clientsMap.values()));

        List<Machine> machines;
        try {
            machines = MachineDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        machinesMap = new HashMap<>();
        for (Machine machine : machines) {
            machinesMap.put(String.valueOf(machine.getCode()), machine.getMachineType());
        }
        //List<String> MachinesCode = machines.stream().map(Machine::getMachineType).collect(Collectors.toList());
        machineComboBox.setItems(FXCollections.observableArrayList(machinesMap.values()));
    }

    @FXML
    private void AssignMachines() throws SQLException {
        String CodeMachine = "";
        String ValueMachine = machineComboBox.getValue();
        for (String key : machinesMap.keySet()) {
            if (machinesMap.get(key).equals(ValueMachine)) {
                CodeMachine = key;
            }
        }

        String CodeClient = "";
        String ValueClient = clientComboBox.getValue();
        for (String key : clientsMap.keySet()) {
            if (clientsMap.get(key).equals(ValueClient)) {
                CodeClient = key;
            }
        }

        int machineCode = 0;
        int clientCode = 0;
        if (CodeMachine != null && CodeMachine.matches("\\d+") && CodeClient != null && CodeClient.matches("\\d+")) {
            machineCode = Integer.parseInt(CodeMachine);
            clientCode = Integer.parseInt(CodeClient);

            Client_MachineDAO.insertMachineToClient(clientCode, machineCode);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No se ha seleccionado nada en la base de datos");
            alert.show();
        }
    }


}

