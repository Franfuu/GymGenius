package com.github.Franfuu.view;

import com.github.Franfuu.App;
import com.github.Franfuu.model.dao.ClientDAO;
import com.github.Franfuu.model.entity.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AppController extends Controller implements Initializable {


    @FXML
    private BorderPane borderPane;
    private Controller centerController;
    public static View loadFXML(Scenes scenes) throws Exception {
        String url = scenes.getURL();
        System.out.println(url);
        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        Parent p = loader.load();
        Controller c = loader.getController();
        View view = new View();
        view.scene = p;
        view.controller = c;
        return view;
    }
    public void changeScene(Scenes scene, Object data) throws Exception {
        View view = loadFXML(scene);
        borderPane.setCenter(view.scene);
        this.centerController = view.controller;
        this.centerController.onOpen(data);
    }

    public void onOpen(Object input) throws Exception {
       changeScene(Scenes.MAINPAGE, null);
    }

    public void onClose(Object output) {
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb)  {

    }


}



