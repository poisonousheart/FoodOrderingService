package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    TableColumn menuNameCol, orderTimeCol, statusCol, priceCol;
    @FXML
    TableView orderTable;

    @FXML
    Label totalPrice;

    public void initialize(){
        orderSlicer = new OrderSlicer();
        orderSlicer.setData("{\"id\":17,\"menu_id\":\"B2\",\"menu_status\":1,\"menu_name\":\"curry rice\",\"price\":120,\"recipe\":\"\",\"image\":\"iVBORw0KGgoAAAANSUhEUgAAAloAAAJxCAYAAACaMBwfAAAAAXNSR0IArs4c6QAAAAlwSFlzAAAXEgAAFxIBZ5\\,\"created_at\":\"2020-11-16T09:50:53.000000Z");
        Order o1 = new Order(orderSlicer.getMenuName(),orderSlicer.getTimeCreated(),
                            orderSlicer.getStatus(), orderSlicer.getPrice());
        orderSlicer.setData("{\"id\":99,\"menu_id\":\"Z8\",\"menu_status\":1,\"menu_name\":\"tar's best dish\",\"price\":99999,\"recipe\":\"\",\"image\":\"iVBORw0KGgoAAAANSUhEUgAAAloAAAJxCAYAAACaMBwfAAAAAXNSR0IArs4c6QAAAAlwSFlzAAAXEgAAFxIBZ5\\,\"created_at\":\"2020-11-16T03:90:43.000000Z");
        Order o2 = new Order(orderSlicer.getMenuName(),orderSlicer.getTimeCreated(),
                orderSlicer.getStatus(), orderSlicer.getPrice());
        //        System.out.println(o1.getName());
//        System.out.println(o1.getCreateTime());
//        System.out.println(o1.getStatus());
//        System.out.println(o1.getPrice());
        //TableColumn menuNameCol = new TableColumn("Menu Name");
        menuNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        //menuNameCol.setId("orderColumn");
        //menuNameCol.setPrefWidth(500);

//        TableColumn orderTimeCol = new TableColumn("Created at");
        orderTimeCol.setCellValueFactory(new PropertyValueFactory<>("createTime"));
        //orderTimeCol.setId("orderColumn");
        //orderTimeCol.setPrefWidth(365);

        //TableColumn statusCol = new TableColumn("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        //statusCol.setId("orderColumn");
        //statusCol.setPrefWidth(325);

        //TableColumn priceCol = new TableColumn("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        //priceCol.setId("orderColumn");
        //priceCol.setPrefWidth(375);

        //orderTable.getColumns().addAll(menuNameCol, orderTimeCol, statusCol, priceCol);
        orderTable.getItems().add(o1);
        orderTable.getItems().add(o2);

        int sum = 0;
        for (Object o: orderTable.getItems()) {
            Order order = (Order) o;
            sum += Integer.valueOf(order.getPrice());
        }
        totalPrice.setText(sum+" Baht");
    }

    @FXML public void backButtonClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MenuController.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}
