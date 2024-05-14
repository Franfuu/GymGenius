package com.github.Franfuu.view;

import com.github.Franfuu.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseRoleController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button adminWayButton;
    @FXML
    private Button clientWayButton;

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setAdminWayButton() throws Exception {
        App.currentController.changeScene(Scenes.REGADMIN, null);
    }
    public void setClientWayButton() throws Exception {
        App.currentController.openModal(Scenes.CLIENTLOGIN, "Bienvenido Cliente...", this, null);
    }
    //public void onBack() throws Exception {
      //  App.currentController.changeScene(Scenes.MAINPAGE, null);
   // }
}
