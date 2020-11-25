package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingController {

    @FXML Label amountLabel;
    @FXML Button increaseButton,decreaseButton,bookButton;

    @FXML public void increaseButtonClick(MouseEvent event) throws IOException {
        Integer amount = Integer.parseInt(amountLabel.getText());
        if (amount < 10){
            amountLabel.setText(String.valueOf(amount+1));
        }
    }

    @FXML public void decreaseButtonClick(MouseEvent event) throws IOException {
        Integer amount = Integer.parseInt(amountLabel.getText());
        if (amount > 1){
            amountLabel.setText(String.valueOf(amount-1));
        }
    }

    @FXML public void bookingButtonClick(MouseEvent event) throws IOException {
        ApiController.putMethod(Main.url+"/api/tables/checkin/"+ReserveTableController.tableNo,"{\"numCus\":\""+amountLabel.getText()+"\"}");
//        System.out.println("{\"numCus\":\""+amountLabel.getText()+"\"}");
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MenuController.fxml"));
        Stage stage = (Stage) bookButton.getScene().getWindow();
        stage.getScene().setRoot(root);
    }

}
