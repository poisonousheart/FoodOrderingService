package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderStatusController {
    @FXML
    Button backButton;

    public void initialize(){

    }

    @FXML public void backButtonClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MenuController.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}
