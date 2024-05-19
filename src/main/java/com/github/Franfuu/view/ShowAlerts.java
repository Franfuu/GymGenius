package com.github.Franfuu.view;

import javafx.scene.control.Alert;

public abstract class ShowAlerts {
    public void showAlerts(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}
