package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderStatusController {

    private OrderSlicer orderSlicer;
    @FXML
    Button backButton;
    @FXML
    TableColumn menuNameCol, qntCol, orderTimeCol, statusCol, priceCol;
    @FXML
    TableView orderTable;

    public void initialize(){
        orderSlicer = new OrderSlicer();
        orderSlicer.setData("{\"id\":17,\"menu_id\":\"B2\",\"menu_status\":1,\"menu_name\":\"curry rice\",\"price\":120,\"recipe\":\"\",\"image\":\"iVBORw0KGgoAAAANSUhEUgAAAloAAAJxCAYAAACaMBwfAAAAAXNSR0IArs4c6QAAAAlwSFlzAAAXEgAAFxIBZ5\\,\"created_at\":\"2020-11-16T09:50:53.000000Z");


    }

    @FXML public void backButtonClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MenuController.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}
