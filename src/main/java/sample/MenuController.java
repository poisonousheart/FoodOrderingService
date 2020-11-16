package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.sql.SQLException;

public class MenuController {

    @FXML ImageView Icon;
    @FXML Button confirmButton, backButton, orderButton;
    @FXML AnchorPane menuPage, confirmPage;

    public void initialize(){
        confirmPage.setVisible(false);
        menuPage.setDisable(false);
    }

    @FXML public void orderButtonClick(MouseEvent event) throws IOException, SQLException {
        confirmPage.setVisible(true);
        menuPage.setDisable(true);
    }

    @FXML public void backButtonClick(MouseEvent event){
        confirmPage.setVisible(false);
        menuPage.setDisable(false);
    }

    @FXML public void confirmButtonClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("OrderStatusController.fxml"));
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}
