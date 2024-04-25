package com.github.Franfuu;

import com.github.Franfuu.view.controller.AppController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static Stage stage;
    public static AppController currentController;



    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
