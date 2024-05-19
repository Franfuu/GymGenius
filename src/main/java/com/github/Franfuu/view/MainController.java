package com.github.Franfuu.view;

import com.github.Franfuu.App;
import com.github.Franfuu.model.dao.ClientDAO;
import com.github.Franfuu.model.entity.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MainController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button addClient;
    @FXML
    private Button deleteClient;
    @FXML
    private Button showMachines;
    @FXML
    private Button showMachinesToClient;
    @FXML
    private Button addMachineToClient;
    @FXML
    private Button deleteMachineToClient;
    @FXML
    private TableView<Client> tableInfo;
    @FXML
    private TableColumn<Client, String> colCode;
    @FXML
    private TableColumn<Client, String> colName;
    @FXML
    private TableColumn<Client, String> colSurname;
    @FXML
    private TableColumn<Client, String> colDNI;
    @FXML
    private TableColumn<Client, String> colEmail;
    @FXML
    private TableColumn<Client, String> colSex;
    @FXML
    private ImageView exitButton;
    @FXML
    private TableColumn<?, ?> colDeleteClient;
    @FXML
    private TableColumn<?, ?> colAddMachine;
    @FXML
    private ObservableList<Client> clientList;

    @Override
    public void onOpen(Object input) throws Exception {
        ClientDAO cdao = new ClientDAO();
        List<Client> clients = cdao.findAll();
        this.clientList = FXCollections.observableArrayList(clients);
        tableInfo.setItems(this.clientList);
    }

    @Override
    public void onClose(Object output) {

    }

    private boolean validateDNI(String dni) {
        // Expresión regular para validar el DNI
        String dniPattern = "\\d{8}[a-zA-Z]";
        return dni.matches(dniPattern);
    }

    private boolean validateEmail(String email) {
        // Expresión regular para validar el correo electrónico
        String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailPattern);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableInfo.setEditable(true);
        tableInfo.setRowFactory(tv -> {
            TableRow<Client> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Client client = tableInfo.getSelectionModel().getSelectedItem();
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        App.currentController.changeScene(Scenes.MACHINESTOCLIENT, client);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

            });
            return row;
        });
        colCode.setCellValueFactory(client -> new SimpleStringProperty(String.valueOf(client.getValue().getCode())));
        //colCode.setCellFactory(TextFieldTableCell.forTableColumn());
        colCode.setOnEditCommit(event -> {
            try {
                if (event.getNewValue().equals(event.getOldValue())) {
                    return;
                }
                if (event.getNewValue().length() <= 60) {
                    Client client = event.getRowValue();
                    client.setCode(Integer.parseInt(event.getNewValue()));
                    ClientDAO.build().update(client);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error, el campo no puede superar los 50 caracteres");
                    alert.show();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error, el valor ingresado no es un número válido");
                alert.show();
            }
        });
        colName.setCellValueFactory(client -> new SimpleStringProperty(client.getValue().getName()));
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colName.setOnEditCommit(event -> {
            try {
                if (event.getNewValue().equals(event.getOldValue())) {
                    return;
                }

                if (event.getNewValue().length() <= 60) {
                    Client client = event.getRowValue();
                    client.setName(event.getNewValue());
                    ClientDAO.build().update(client);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error, el campo no puede superar los 50 caracteres");
                    alert.show();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error, el valor ingresado no es un número válido");
                alert.show();
            }
        });
        colSurname.setCellValueFactory(client -> new SimpleStringProperty(client.getValue().getSurname()));
        colSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        colSurname.setOnEditCommit(event -> {
            try {
                if (event.getNewValue().equals(event.getOldValue())) {
                    return;
                }

                if (event.getNewValue().length() <= 60) {
                    Client client = event.getRowValue();
                    client.setSurname(event.getNewValue());
                    ClientDAO.build().update(client);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error, el campo no puede superar los 50 caracteres");
                    alert.show();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error, el valor ingresado no es un número válido");
                alert.show();
            }
        });
        colDNI.setCellValueFactory(client -> new SimpleStringProperty(client.getValue().getDni()));
        colDNI.setCellFactory(TextFieldTableCell.forTableColumn());
        colDNI.setOnEditCommit(event -> {
            boolean shouldUpdate = true;
            try {
                String newValue = event.getNewValue().trim();

                if (newValue.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Error", "El DNI no puede estar vacío.");
                    shouldUpdate = false;
                } else if (!validateDNI(newValue)) {
                    showAlert(Alert.AlertType.ERROR, "Error", "El formato del DNI no es válido.");
                    shouldUpdate = false;
                } else if (newValue.equals(event.getOldValue())) {
                    shouldUpdate = false;
                }

                // Verificar longitud del DNI
                else if (newValue.length() > 9) {
                    showAlert(Alert.AlertType.ERROR, "Error", "El DNI no puede superar los 9 caracteres incluyendo la letra.");
                    shouldUpdate = false;
                }

                if (shouldUpdate) {
                    Client client = event.getRowValue();
                    client.setDni(newValue);
                    ClientDAO.build().update(client);
                }
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "El valor ingresado no es válido.");
                shouldUpdate = false;
            }
        });
        colEmail.setCellValueFactory(client -> new SimpleStringProperty(client.getValue().getEmail()));
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setOnEditCommit(event -> {
            boolean shouldUpdate = true;
            try {
                String newValue = event.getNewValue().trim();

                if (newValue.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Error", "El correo electrónico no puede estar vacío.");
                    shouldUpdate = false;
                } else if (!validateEmail(newValue)) {
                    showAlert(Alert.AlertType.ERROR, "Error", "El formato del correo electrónico no es válido.");
                    shouldUpdate = false;
                } else if (newValue.equals(event.getOldValue())) {
                    shouldUpdate = false;
                } else if (newValue.length() > 60) {
                    showAlert(Alert.AlertType.ERROR, "Error", "El correo electrónico no puede superar los 60 caracteres.");
                    shouldUpdate = false;
                }

                if (shouldUpdate) {
                    Client client = event.getRowValue();
                    client.setEmail(newValue);
                    ClientDAO.build().update(client);
                }
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "El valor ingresado no es válido.");
                shouldUpdate = false;
            }
        });

        colSex.setCellValueFactory(client -> new SimpleStringProperty(client.getValue().getSex()));
        colSex.setCellFactory(TextFieldTableCell.forTableColumn());
        colSex.setOnEditCommit(event -> {
            try {
                if (event.getNewValue().equals(event.getOldValue())) {
                    return;
                }

                if (event.getNewValue().length() <= 60) {
                    Client client = event.getRowValue();
                    client.setSex(event.getNewValue());
                    ClientDAO.build().update(client);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error, el campo no puede superar los 50 caracteres");
                    alert.show();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error, el valor ingresado no es un número válido");
                alert.show();
            }
        });
    }

    public void openAddClient() throws Exception {
        //App.currentController.changeScene(Scenes.ADDCLIENT, null);
        App.currentController.openModal(Scenes.ADDCLIENT, "Agregando cliente...", this, null);
    }

    public void openDeleteClient() throws Exception {
        //App.currentController.changeScene(Scenes.ADDCLIENT, null);
        App.currentController.openModal(Scenes.DELETECLIENT, "Eliminando cliente...", this, null);
    }

    public void openShowMachines() throws Exception {
        App.currentController.changeScene(Scenes.SHOWMACHINES, null);
    }

    public void openAddMachineClient() throws Exception {
        App.currentController.openModal(Scenes.ADDMACHINETOCLIENT, "Agregando cliente a la maquina...", this, null);
    }

    public void openDeleteMachineClient() throws Exception {
        App.currentController.openModal(Scenes.DELETEMACHINETOCLIENT, "Eliminando maquina asociada...", this, null);
    }


    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}