package com.github.Franfuu.view;

import com.github.Franfuu.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.events.Event;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowMachinesToClientsController extends Controller implements Initializable {
    @FXML
    private Button showClientMachineButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField fieldClientCode;
    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void showClientMachine(Event event) throws Exception {
        App.currentController.changeScene(Scenes.SHOWMACHINESTOCLIENT, null);
    }
}
