package com.github.Franfuu.view;

import com.github.Franfuu.model.entity.Machine;
import com.github.Franfuu.model.entity.Room;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;


public class AddMachineController extends Controller implements Initializable {
    @FXML
    private Button addMachineButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField fiedName;
    @FXML
    private TextField fieldRoom;

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
