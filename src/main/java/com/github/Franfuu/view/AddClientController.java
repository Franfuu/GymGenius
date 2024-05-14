package com.github.Franfuu.view;

import com.github.Franfuu.App;
import com.github.Franfuu.model.dao.ClientDAO;
import com.github.Franfuu.model.entity.Client;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddClientController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button addClientButton;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldSurname;
    @FXML
    private TextField fieldDni;
    @FXML
    private TextField fieldEmail;
    @FXML
    private TextField fieldPassword;
    @FXML
    private TextField fieldSex;


    @Override
    public void onOpen(Object input) throws Exception {
      //  this.controller = (MainController) input;
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void onSave(Event event) throws Exception {
        String name = fieldName.getText();
        String surname = fieldSurname.getText();
        String dni = fieldDni.getText();
        String email = fieldEmail.getText();
        String password = fieldPassword.getText();
        String sex = fieldSex.getText();

        Client client = new Client(name, surname,email, password,  dni,  sex);
        ClientDAO cdao = new ClientDAO();
        cdao.save(client);
        App.currentController.changeScene(Scenes.MAINPAGE, null);
        ((Node)(event.getSource())).getScene().getWindow().hide();

    }


}