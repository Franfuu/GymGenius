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

    }

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String email = clientEmail.getText();
        String password = clientPassword.getText();

        ClientDAO clientDAO = new ClientDAO();
        Client cliente = clientDAO.findByEmail(email);

        if (cliente != null && cliente.getPassword().equals(password)) {

            Session session = Session.getInstance();
            session.logIn(cliente);


        } else {


        }
    }
}
