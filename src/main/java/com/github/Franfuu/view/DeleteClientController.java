package com.github.Franfuu.view;

import com.github.Franfuu.App;
import com.github.Franfuu.model.dao.ClientDAO;
import com.github.Franfuu.model.dao.Client_MachineDAO;
import com.github.Franfuu.model.dao.MachineDAO;
import com.github.Franfuu.model.entity.Client;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteClientController extends Controller implements Initializable {
    @FXML
    private Button deleteClientButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField fieldClientCode;
    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deleteClientButton.setOnAction(event -> {
            try {
                deleteClient(event);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void deleteClient(Event event) throws SQLException {
        String clientCode = fieldClientCode.getText().trim();

        if (clientCode.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "El campo del código del cliente no puede estar vacío.");
        } else {
            try {
                if (!clientCode.matches("\\d+")) {
                    showAlert(Alert.AlertType.ERROR, "Error", "El código del cliente debe contener solo números.");
                    return;
                }
                ClientDAO cdao = new ClientDAO();
                boolean deleted = cdao.delete(Integer.parseInt(clientCode));

                if (deleted) {
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "El cliente se ha eliminado correctamente.");
                    App.currentController.changeScene(Scenes.MAINPAGE, null);
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "El código del cliente no coincide con ninguna máquina en la base de datos.");
                }
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "El código del cliente debe ser un número válido.");
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Error al eliminar al cliente de la base de datos.");
                e.printStackTrace();
            }
        }

    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
