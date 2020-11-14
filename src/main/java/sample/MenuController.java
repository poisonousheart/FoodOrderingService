package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML ImageView Icon;
    @FXML Button confirmButton, backButton, orderButton;

    public void initialize(){
        confirmButton.setVisible(false);
        backButton.setVisible(false);
        orderButton.setVisible(true);
    }

    @FXML public void exitButton(MouseEvent event) throws IOException {
        System.out.println("Exit Program");
        Label area = (Label) event.getSource();
        Stage regisStage = (Stage) area.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registerScene.fxml"));
        regisStage.setScene(new Scene((Parent) loader.load(),800, 600));
        MenuController menuScene = loader.getController();
    }

    @FXML public void orderButtonClick(MouseEvent event){
        confirmButton.setVisible(true);
        backButton.setVisible(true);
        orderButton.setVisible(false);
    }

    @FXML public void backButtonClick(MouseEvent event){
        confirmButton.setVisible(false);
        backButton.setVisible(false);
        orderButton.setVisible(true);
    }

    @FXML public void confirmButtonClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("OrderStatusController.fxml"));
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}
