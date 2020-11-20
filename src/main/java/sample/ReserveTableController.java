package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ReserveTableController {

    public static String tableNo;

    @FXML
    Label statusField;

    @FXML Button tableNo1, tableNo2, tableNo3, tableNo4, tableNo5, tableNo6, tableNo7, tableNo8, tableNo9, tableNo10;

    public void initialize() throws IOException {
        statusField.setVisible(false);
    }

    @FXML public void selectNo(MouseEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        //System.out.println(btn.getText());
        String selected = ApiController.getMethod("http://42de8d7e28e8.ngrok.io/api/tables/getStatus/"+btn.getText());
        if (selected.equals("0")) {
            statusField.setVisible(false);
            tableNo = btn.getText();
            ApiController.getMethod("http://42de8d7e28e8.ngrok.io/api/tables/initTable/"+btn.getText());
//            System.out.println(tableNo);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("BookingController.fxml"));
            Stage stage = (Stage) btn.getScene().getWindow();
            stage.getScene().setRoot(root);
        }
        else {
            statusField.setVisible(true);
        }
    }

}
