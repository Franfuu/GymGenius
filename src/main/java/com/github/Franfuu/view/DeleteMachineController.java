package com.github.Franfuu.view;

import com.github.Franfuu.App;
import com.github.Franfuu.model.dao.MachineDAO;
import com.github.Franfuu.model.dao.RoomDAO;
import com.github.Franfuu.model.entity.Machine;
import com.github.Franfuu.model.entity.Room;
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

public class DeleteMachineController extends Controller implements Initializable {

    @FXML
    private Button deleteMachineButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField fieldMachineCode;

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deleteMachineButton.setOnAction(event -> {
            try {
                deleteMachine(event);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void deleteMachine(Event event) throws SQLException {
        String machineCode = fieldMachineCode.getText().trim();

        if (machineCode.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "El campo del código de máquina no puede estar vacío.");
        } else {
            try {
                if (!machineCode.matches("\\d+")) {
                    showAlert(Alert.AlertType.ERROR, "Error", "El código de máquina debe contener solo números.");
                    return;
                }
                MachineDAO mdao = new MachineDAO();
                boolean deleted = mdao.delete(Integer.parseInt(machineCode));

                if (deleted) {
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "La máquina se ha eliminado correctamente.");
                    App.currentController.changeScene(Scenes.SHOWMACHINES, null);
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "El código de máquina no coincide con ninguna máquina en la base de datos.");
                }
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "El código de máquina debe ser un número válido.");
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Error al eliminar la máquina de la base de datos.");
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
