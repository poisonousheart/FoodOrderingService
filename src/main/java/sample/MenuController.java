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

public class MenuController {

    private MenuSlicer menuSlicer;
    @FXML ImageView Icon, testImg;
    @FXML Button confirmButton, backButton, orderButton;
    @FXML AnchorPane menuPage, confirmPage;

    public void initialize(){
        confirmPage.setVisible(false);
        menuPage.setDisable(false);
        menuSlicer = new MenuSlicer();
    }

    @FXML public void orderButtonClick(MouseEvent event){
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

    //code for getting image
//            menuSlicer.setData("http://f51fced2af20.ngrok.io/api/menus");
//        menuSlicer.debug();
//    Image image = menuSlicer.getImage();
//        testImg.setImage(image);
}
