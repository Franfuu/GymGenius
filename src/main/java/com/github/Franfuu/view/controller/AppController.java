package com.github.Franfuu.view.controller;

import com.github.Franfuu.App;
import com.github.Franfuu.view.Scenes;
import com.github.Franfuu.view.View;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController extends ControllerOpCl implements Initializable {
    @FXML
    private BorderPane borderPane;
    private ControllerOpCl centerController;

    @Override
    public void onOpen(Object input) throws IOException {
        //Al abrir este controlador que cargue main en el centro
        changeScene(Scenes.MAINPAGE,null);
    }

    public void changeScene(Scenes scene,Object data) throws IOException {
        View view = loadFXML(scene);
        borderPane.setCenter(view.scene);
        this.centerController = view.controller;
        this.centerController.onOpen(data);
    }

    public void openModal(Scenes scene, String title,ControllerOpCl parent, Object data) throws IOException {
        View view = loadFXML(scene);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(App.stage);
        Scene _scene = new Scene(view.scene);
        stage.setScene(_scene);
        view.controller.onOpen(parent);
        stage.showAndWait();
        //podríamos leer que ha devuelto...

    }


    @Override
    public void onClose(Object output) {
        //nothing to do
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static View loadFXML(Scenes scenes) throws IOException {
        String url = scenes.getURL();
        System.out.println(url);
        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        Parent p = loader.load();
        ControllerOpCl c = loader.getController();
        View view = new View();
        view.scene=p;
        view.controller=c;
        return view;
    }

    @FXML
    private void closeApp(){
        System.exit(0);
    }

    @FXML
    private void goToAbout() throws IOException {
        System.out.println(Scenes.ADDCLIENT);
        changeScene(Scenes.ADDCLIENT,null);
    }
}