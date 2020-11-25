package sample;

import javafx.application.Platform;
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
import javafx.scene.layout.AnchorPane;
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
    AnchorPane confirmPage, confirmAnywayPage, orderStatusPage;

    @FXML
    Label totalPrice, tableNo;

    public void initialize() throws IOException {
                tableNo.setText(ReserveTableController.tableNo);
                confirmPage.setVisible(false);
                confirmAnywayPage.setVisible(false);
                orderStatusPage.setVisible(true);
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
                String jsonFormat = ApiController.getMethod(Main.url+"/api/orders/getByTable/"+tableNo.getText());
                String[] jsonString = jsonFormat.split("[{]");
        for (String s:jsonString)
            System.out.println("order status check "+s);

                for (int i = 2; i < jsonString.length; i++) {
                    String[] orderString = jsonString[i].split("[:,\"]");
           for(String s: orderString)
               System.out.println("orderString check "+s);
                    ArrayList<String> order = new ArrayList<>();
                    for (int j = 0; j < orderString.length; j++) {
                        if(orderString[j] != null && !orderString[j].trim().isEmpty()) {
                            order.add(orderString[j]);
                        }
                    }
            for (String s: order)
                System.out.println("order check "+s);

//                    System.out.println(order);


                    //create order to display
                    String menuId = order.get(order.indexOf("menu_id")+1);
                    String status = order.get(order.indexOf("status")+1);

                    int index = order.indexOf("updated_at");
                    System.out.println("fucking bug "+order.get(index));
                    String[] tmp = order.get(index+1).split("T");
                    String[] tmp2 = order.get(index+3).split("\\.");


                    System.out.println("tmp = "+tmp);
                    System.out.println("tmp2 = "+tmp2);

                    String time = tmp[1] +":"+order.get(index+2)+":"+ tmp2[0];
                    String dbUrl = Main.url+"api/menus/searchNoImage/";
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

    @FXML public void checkbillButtonClick(MouseEvent event) {
//        if (){ ------------------ < -ใส่เพื่อเช็คว่ามีรายการอาหารทำเสร็จหมดแล้วหรือยัง ถ้ายัง
//
//            confirmPage.setVisible(false);
//            confirmAnywayPage.setVisible(true);
//            orderStatusPage.setDisable(true);
//        }
//        else { ------------------ < - รายการอาหารทำเสร็จหมดแล้ว
//        confirmPage.setVisible(true);
//        confirmAnywayPage.setVisible(false);
//        orderStatusPage.setDisable(true);
//        }
    }

    @FXML public void noButtonClick(MouseEvent event) throws IOException {
        confirmPage.setVisible(false);
        confirmAnywayPage.setVisible(false);
        orderStatusPage.setDisable(false);
    }

    @FXML public void yesButtonClick(MouseEvent event) throws IOException {
        ApiController.deleteMethod(Main.url+"/api/orders/"+tableNo.getText());
        ApiController.getMethod(Main.url+"/api/tables/checkout/"+tableNo.getText());
        //        System.out.println("http://42de8d7e28e8.ngrok.io/api/orders/"+tableNo.getText());
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("BookingController.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.getScene().setRoot(root);
    }


}
