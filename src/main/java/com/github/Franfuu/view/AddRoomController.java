package com.github.Franfuu.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddRoomController extends Controller implements Initializable {
    @FXML
    private VBox vBox;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField fieldRoom;
    @FXML
    private Button addRoomButton;

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
