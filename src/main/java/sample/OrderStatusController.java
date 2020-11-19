package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class OrderStatusController {

    private OrderSlicer orderSlicer;
    private MenuBuilder menuBuilder;
    @FXML
    Button backButton;
    @FXML
    TableColumn menuNameCol, orderTimeCol, statusCol, priceCol;
    @FXML
    TableView orderTable;

    @FXML
    Label totalPrice, tableNo;

    public void initialize() throws IOException {
        orderSlicer = new OrderSlicer();
        menuBuilder = new MenuBuilder();
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
//        orderSlicer.setData("{\"id\":17,\"menu_id\":\"B2\",\"menu_status\":1,\"menu_name\":\"curry rice\",\"price\":120,\"recipe\":\"\",\"image\":\"iVBORw0KGgoAAAANSUhEUgAAAloAAAJxCAYAAACaMBwfAAAAAXNSR0IArs4c6QAAAAlwSFlzAAAXEgAAFxIBZ5\\,\"created_at\":\"2020-11-16T09:50:53.000000Z");
//        Order o1 = new Order(orderSlicer.getMenuName(),orderSlicer.getTimeCreated(),
//                orderSlicer.getStatus(), orderSlicer.getPrice());
//        orderSlicer.setData("{\"id\":99,\"menu_id\":\"Z8\",\"menu_status\":1,\"menu_name\":\"tar's best dish\",\"price\":99999,\"recipe\":\"\",\"image\":\"iVBORw0KGgoAAAANSUhEUgAAAloAAAJxCAYAAACaMBwfAAAAAXNSR0IArs4c6QAAAAlwSFlzAAAXEgAAFxIBZ5\\,\"created_at\":\"2020-11-16T03:90:43.000000Z");
//        Order o2 = new Order(orderSlicer.getMenuName(),orderSlicer.getTimeCreated(),
//                orderSlicer.getStatus(), orderSlicer.getPrice());
//        orderTable.getItems().add(o1);
//        orderTable.getItems().add(o2);
        //---------------------------------------URL--------------------------
        String jsonFormat = ApiController.getMethod("http://4d8e9aa5673b.ngrok.io/api/orders/getByTable/"+tableNo.getText());
        String[] jsonString = jsonFormat.split("[{]");
//        for (String s:jsonString)
//            System.out.println("order status check "+s);
        for (int i = 1; i < jsonString.length; i++) {
           String[] orderString = jsonString[i].split("[:,\"]");
//           for(String s: orderString)
//               System.out.println("item check "+s);
            ArrayList<String> order = new ArrayList<>();
            for (int j = 0; j < orderString.length; j++) {
                if(orderString[j] != null && !orderString[j].trim().isEmpty()) {
                    order.add(orderString[j]);
                }
            }
//            for (String s: order)
//                System.out.println("order check "+s);

            //create order to display
            String menuId = order.get(order.indexOf("menu_id")+1);
            String status = order.get(order.indexOf("status")+1);

            int index = order.indexOf("updated_at");
            String[] tmp = order.get(index+1).split("T");
            String[] tmp2 = order.get(index+3).split("\\.");
            String time = tmp[1] +":"+order.get(index+2)+":"+ tmp2[0];
            String dbUrl = "http://4d8e9aa5673b.ngrok.io/api/menus/searchNoImage/";
//            GridPane menu = menuBuilder.getMenu(dbUrl+menuId);
            //name and price
//            String menuName = String.valueOf(menu.getChildren().get(1)).split("'")[1];
//            String menuPrice = String.valueOf(menu.getChildren().get(2)).split("'")[1];
            String menuString = ApiController.getMethod(dbUrl+menuId);
            String[] menuItem = menuString.split("[{},:\\[\\]\"]");
            String menuName = menuItem[6];
            String menuPrice = menuItem[11];

            Order o = new Order(menuName, time, status, menuPrice);
            orderTable.getItems().add(o);
        }

        int sum = 0;
        for (Object o: orderTable.getItems()) {
            Order order = (Order) o;
            sum += Integer.valueOf(order.getPrice());
        }
        totalPrice.setText(NumberFormat.getNumberInstance(Locale.US).format(sum)+" Baht");
    }

    @FXML public void backButtonClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MenuController.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}
