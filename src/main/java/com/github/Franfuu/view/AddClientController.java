package com.github.Franfuu.view;

import com.github.Franfuu.App;
import com.github.Franfuu.model.dao.ClientDAO;
import com.github.Franfuu.model.entity.Client;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
    @FXML
    public void onSave(Event event) {
        try {
            String name = fieldName.getText().trim();
            String surname = fieldSurname.getText().trim();
            String dni = fieldDni.getText().trim();
            String email = fieldEmail.getText().trim();
            String password = fieldPassword.getText().trim();
            String sex = fieldSex.getText().trim();

            if (name.isEmpty() || surname.isEmpty() || dni.isEmpty() || email.isEmpty() || password.isEmpty() || sex.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Campos Vacíos", "Por favor, complete todos los campos.");
            } else {
                // Validar DNI
                if (!validateDNI(dni)) {
                    showAlert(Alert.AlertType.ERROR, "Error en DNI", "El formato del DNI no es válido.");
                } else {
                    // Validar correo electrónico
                    if (!validateEmail(email)) {
                        showAlert(Alert.AlertType.ERROR, "Error en Correo Electrónico", "El formato del correo electrónico no es válido.");
                    } else {
                        // Guardar el cliente
                        Client client = new Client( name, surname, email, password, dni, sex);
                        ClientDAO cdao = new ClientDAO();
                        cdao.save(client);
                        showAlert(Alert.AlertType.INFORMATION, "Cliente Agregado", "El cliente se ha agregado correctamente.");
                        App.currentController.changeScene(Scenes.MAINPAGE, null);
                        ((Node) (event.getSource())).getScene().getWindow().hide();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error al Guardar", "Hubo un error al intentar guardar el cliente.");
        }
    }

    private boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }


    private boolean validateDNI(String dni) {
        String dniRegex = "\\d{8}[a-zA-Z]";
        return dni.matches(dniRegex);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


}