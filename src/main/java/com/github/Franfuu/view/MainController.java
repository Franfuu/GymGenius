package com.github.Franfuu.view;

import com.github.Franfuu.model.dao.ClientDAO;
import com.github.Franfuu.model.entity.Client;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button addClient;
    @FXML
    private Button addMachine;
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
    private ImageView exitButton;
    @FXML
    private TableColumn<?, ?> colDeleteClient;
    @FXML
    private TableColumn<?, ?> colAddMachine;
    @FXML
    private ObservableList<Client> clientList;

    public void saveClient(Client newClient) throws SQLException {
        ClientDAO.build().save(newClient);
        this.clientList.add(newClient);

    }

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    /*  tableInfo.setEditable(true);
        colCode.setCellValueFactory(client -> new SimpleStringProperty(String.valueOf(client.getValue().getClientCode())));
        colName.setCellValueFactory(client -> new SimpleStringProperty(client.getValue().getName()));
        colSurname.setCellValueFactory(client -> new SimpleStringProperty(client.getValue().getSurname()));
        colDNI.setCellValueFactory(client -> new SimpleStringProperty(client.getValue().getDni()));
        colTLF.setCellValueFactory(client -> new SimpleStringProperty(client.getValue().getPhone()));
        colCode.setCellFactory(TextFieldTableCell.forTableColumn());
        colCode.setOnEditCommit(event -> {
            if (event.getNewValue() == event.getNewValue()) {
                return;
            }
            if (event.getNewValue().length() <= 50) {
                Client client = event.getRowValue();
                client.setClientCode(Integer.parseInt(event.getNewValue()));
                try {
                    ClientDAO.build().save(client);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error, el campo no puede superar los 50 caracteres");
                alert.show();
            }
        });
    */
    }
}



