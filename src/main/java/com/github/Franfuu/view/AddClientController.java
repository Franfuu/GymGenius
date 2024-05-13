package com.github.Franfuu.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddClientController extends Controller implements Initializable {
    @FXML
    private VBox vBox;
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

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}