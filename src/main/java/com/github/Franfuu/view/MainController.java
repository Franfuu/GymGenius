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
                if (event.getNewValue().equals(event.getOldValue())) {
                    return;
                }

                if (event.getNewValue().length() <= 60) {
                    Client client = event.getRowValue();
                    client.setDni(event.getNewValue());
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
        colEmail.setCellValueFactory(client -> new SimpleStringProperty(client.getValue().getEmail()));
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colSurname.setOnEditCommit(event -> {
            try {
                if (event.getNewValue().equals(event.getOldValue())) {
                    return;
                }

                if (event.getNewValue().length() <= 60) {
                    Client client = event.getRowValue();
                    client.setEmail(event.getNewValue());
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




    public void saveClient(Client newClient)  {
        ClientDAO.build().save(newClient);
        this.clientList.add(newClient);
    }
}