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

        // Verificar la autenticación del cliente con el DAO
        ClientDAO clientDAO = new ClientDAO();
        Client cliente = clientDAO.findByEmail(email);

        if (cliente != null && cliente.getPassword().equals(password)) {
            // Inicio de sesión exitoso, almacenar el cliente en la sesión
            Session session = Session.getInstance();
            session.logIn(cliente);
            System.out.println("Inicio de sesión exitoso para el cliente: " + cliente.getName());
            // Cerrar la ventana de inicio de sesión y abrir la ventana principal del cliente
            // primaryStage.close();
            // Abrir ventana principal del cliente
            // openMainClienteWindow();
        } else {
            System.out.println("Inicio de sesión fallido. Email o contraseña incorrectos.");

        }
    }
}
