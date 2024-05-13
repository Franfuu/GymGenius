package com.github.Franfuu.view;

import com.github.Franfuu.App;
import com.github.Franfuu.model.dao.ClientDAO;
import com.github.Franfuu.model.entity.Client;
import com.github.Franfuu.utils.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientLoginController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button clientLoginButton;
    @FXML
    private TextField clientEmail;
    @FXML
    private TextField clientPassword;

    private ClientDAO clientDAO = new ClientDAO();

    @FXML
    public void login() throws IOException {
        String email = clientEmail.getText();
        String password = clientPassword.getText();

        Client clientLogin = ClientDAO.build().findByClientCode(Integer.valueOf(email));

        if (clientLogin != null) {
            if (password.equals(clientLogin.getPassword()) && email.equals(clientLogin.getEmail())) {
                Session.getInstance().login(clientLogin);
                //App.start("clientMachine");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Contraseña invalida, introduzca una contraseña valida");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Usuario incorrecto, introduzca bien el nombre de usuario");
            alert.show();

        }
    }

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
