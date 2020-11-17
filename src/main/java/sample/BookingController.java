package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingController {

        @FXML ChoiceBox amount;
        @FXML Button bookButton;

    @FXML public void bookingButtonClick(MouseEvent event) throws IOException {
        //IOExceptionString amountOfCustomer = (String) amount.getValue();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MenuController.fxml"));
        Stage stage = (Stage) bookButton.getScene().getWindow();
        stage.getScene().setRoot(root);
    }
    }
