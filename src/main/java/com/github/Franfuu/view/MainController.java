package com.github.Franfuu.view;

import com.github.Franfuu.App;
import com.github.Franfuu.model.dao.ClientDAO;
import com.github.Franfuu.model.entity.Client;
import com.github.Franfuu.utils.Session;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.github.Franfuu.view.AppController.loadFXML;

public class MainController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button addClient;
    @FXML
    private Button showMachines;
    @FXML
    private Button addRoom;
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
            try {
                if (event.getNewValue().trim().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Error", "El DNI no puede estar vacío.");
                    return;
                }
                if (!validateDNI(event.getNewValue())) {
                    showAlert(Alert.AlertType.ERROR, "Error", "El formato del DNI no es válido.");
                    return;
                }
                if (event.getNewValue().equals(event.getOldValue())) {
                    return;
                }

                if (event.getNewValue().length() <= 60) {
                    Client client = event.getRowValue();
                    client.setDni(event.getNewValue());
                    ClientDAO.build().update(client);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "El DNI no puede superar los 9 caracteres incluyendo la letra.");
                }
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "El valor ingresado no es válido.");
            }
        });
        colEmail.setCellValueFactory(client -> new SimpleStringProperty(client.getValue().getEmail()));
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setOnEditCommit(event -> {
            try {
                if (event.getNewValue().trim().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Error", "El correo electrónico no puede estar vacío.");
                    return;
                }
                if (!validateEmail(event.getNewValue())) {
                    showAlert(Alert.AlertType.ERROR, "Error", "El formato del correo electrónico no es válido.");
                    return;
                }
                if (event.getNewValue().equals(event.getOldValue())) {
                    return;
                }

                if (event.getNewValue().length() <= 60) {
                    Client client = event.getRowValue();
                    client.setEmail(event.getNewValue());
                    ClientDAO.build().update(client);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "El correo electrónico no puede superar los 60 caracteres.");
                }
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "El valor ingresado no es válido.");
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
    public void openShowMachines() throws Exception {
        App.currentController.changeScene(Scenes.SHOWMACHINES, null);
    }
    public void openAddMachineClient() throws Exception {
        App.currentController.changeScene(Scenes.ADDMACHINETOCLIENT, null);
    }
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void saveClient(Client newClient)  {
        ClientDAO.build().save(newClient);
        this.clientList.add(newClient);
    }
}