package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MenuController {

    private Timeline timeline = new Timeline();
    private MenuBuilder menuBuilder;
    private OrderBuilder orderBuilder;
    @FXML Label categoryName, totalPrice, tableNo;
    @FXML Button confirmButton, backButton, orderButton,b1,b2,b3,b4,b5,b6,riceCate,noodleCate,hotpotCate,appetizerCate,sushiCate,beverageCate;
    @FXML AnchorPane menuPage, confirmPage, menuDisplayPane, orderDisplayPane;
    @FXML ScrollPane menuDisplayScroll, orderDisplayScroll;

    public void initialize() throws IOException{
        tableNo.setText(ReserveTableController.tableNo);
        confirmPage.setVisible(false);
        menuPage.setDisable(false);

        categoryName.setText("Rices");
        //categoryName.setAlignment(Pos.CENTER);
        menuBuilder = new MenuBuilder();
        orderBuilder = new OrderBuilder();

        menuDisplayPane.prefHeightProperty().bind(menuDisplayScroll.maxHeightProperty());
        menuDisplayPane.prefWidthProperty().bind(menuDisplayScroll.maxWidthProperty());
        orderDisplayPane.prefHeightProperty().bind(orderDisplayScroll.maxHeightProperty());
        orderDisplayPane.prefWidthProperty().bind(orderDisplayScroll.maxWidthProperty());
        setCateClickEvent("A");

        Duration startDuration = Duration.ZERO;
        Duration endDuration = Duration.seconds(0.5);

        KeyValue startKeyValue = new KeyValue(categoryName.translateYProperty(), 20);
        KeyFrame startKeyFrame = new KeyFrame(startDuration, startKeyValue);
        KeyValue endKeyValue = new KeyValue(categoryName.translateYProperty(), -20);
        KeyFrame endKeyFrame = new KeyFrame(endDuration, endKeyValue);

        // Create a Timeline
        timeline = new Timeline(startKeyFrame, endKeyFrame);
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
        for(Node n:orderDisplayPane.getChildren()) {
            GridPane order = (GridPane)n;
            Label orderNameLabel = (Label)order.getChildren().get(1);
            //System.out.println("fucking label "+orderNameLabel);
            String orderName = String.valueOf(orderNameLabel).split("'")[1];
            orderName = "\""+orderName+"\"";
            Label qntLabel = (Label)order.getChildren().get(4);
            String qntString = String.valueOf(qntLabel).split("'")[1];
            String tableNoString = String.valueOf(tableNo).split("'")[1];

            int qnt = Integer.valueOf(qntString);
            for (int i = 0; i < qnt; i++) {
//                System.out.println("orderName "+orderName);
                //send table, menuId to DB
                //------------------------URL--------------------
                //{"menu_name":"salmon","table_number":"1"}

                ApiController.postMethod(Main.url+"/api/orders/",
                        "{\"menu_name\":"+orderName+",\"table_number\":\""+tableNoString+"\"}");
//                System.out.println("{\"menu_name\":"+orderName+",\"table_number\":\""+tableNoString+"\"}");
//                System.out.println("{\"menu_name\":\"Unadon\",\"table_number\":\"1\"}\n");
            }

        }
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("OrderStatusController.fxml"));
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    @FXML public void categoryButtonClick(MouseEvent event) throws IOException {
        Button button = (Button) event.getSource();
        String id = button.getId();
            if(id.equals("b1")) {
                categoryName.setText("Rices");
                setCateClickEvent("A");
            }
            else if (id.equals("b2")){
                categoryName.setText("Noodles");
                setCateClickEvent("B");
            }
            else if (id.equals("b3")) {
                categoryName.setText("Hot Pots");
                setCateClickEvent("C");
            }
            else if (id.equals("b4")) {
                categoryName.setText("Appetizers");
                setCateClickEvent("D");
            }
            else if (id.equals("b5")) {
                categoryName.setText("Sushi");
                setCateClickEvent("E");
            }
            else if (id.equals("b6")){
                categoryName.setText("Beverages");
                setCateClickEvent("F");
            }
            categoryName.setAlignment(Pos.CENTER);
            timeline.playFromStart();
            TextSizeTransition trans = new TextSizeTransition(categoryName, 2, 50,Duration.seconds(0.5));
            trans.play();
    }

    @FXML public void mouseEntered(MouseEvent event) {
        Button button = (Button) event.getSource();
        String id = button.getId();
        if(id.equals("b1")) riceCate.setText("Rices >>");
        else if (id.equals("b2")) noodleCate.setText("Noodles >>");
        else if (id.equals("b3")) hotpotCate.setText("Hot Pots >>");
        else if (id.equals("b4")) appetizerCate.setText("Appetizers >>");
        else if (id.equals("b5")) sushiCate.setText("Sushi >>");
        else if (id.equals("b6"))beverageCate.setText("Beverages >>");
        categoryName.setAlignment(Pos.CENTER);
    }

    @FXML public void mouseExited(MouseEvent event) {
        Button button = (Button) event.getSource();
        String id = button.getId();
        if (id.equals("b1")) riceCate.setText("Rices");
        else if (id.equals("b2")) noodleCate.setText("Noodles");
        else if (id.equals("b3")) hotpotCate.setText("Hot Pots");
        else if (id.equals("b4")) appetizerCate.setText("Appetizers");
        else if (id.equals("b5")) sushiCate.setText("Sushi");
        else if (id.equals("b6")) beverageCate.setText("Beverages");
    }

    @FXML private void setCateClickEvent(String cate) throws IOException {
        menuDisplayPane.getChildren().clear();
        String json = ApiController.getMethod(Main.url+"/api/menus/getAll");
        String[] jsonFormat = json.split("[{}:,\\[\\]\"]");
        ArrayList<String> idList = new ArrayList<>();
        ArrayList<String> cateList = new ArrayList<>();

        for (int i = 7; i < jsonFormat.length; i++) {
            if(jsonFormat[i] != null && !jsonFormat[i].trim().isEmpty()) {
                idList.add(jsonFormat[i]);
            }
        }
//        for (String s : idList){
//            System.out.println(s);
//        }

        int menuCount = 0;
        for(String s: idList){
            if(s.contains(cate)) {
                menuCount++;
                cateList.add(s);
            }
        }

//        for (String s: cateList)
//            System.out.println(s);


        //---------------URL------------------------
        String dbUrl = Main.url+"/api/menus/search/";
        int xLayout = 20, yLayout = 0, maxPerLine = 3;
        GridPane menu;
        for (int i = 0; i < menuCount; i++) {
            menu = menuBuilder.getMenu(dbUrl+cateList.get(i));

            menu.setOnMouseClicked(event -> menuClickEvent(event));

            //left placement
            if(i % maxPerLine == 0){
                xLayout = 20;
            }

            //mimddle and right placement
            else {
                xLayout += 490;
            }

            menu.setLayoutX(xLayout);
            menu.setLayoutY(yLayout);
            menuDisplayPane.getChildren().add(menu);
            //check if end of line
            yLayout += i % maxPerLine==maxPerLine-1?350:0;
        }
    }

    @FXML private void menuClickEvent(MouseEvent event){
        GridPane menu = (GridPane) event.getSource();
        //System.out.println(menu.getChildren().get(1));
        String[] menuNametmp = String.valueOf(menu.getChildren().get(1)).split("'");
        String menuName = menuNametmp[menuNametmp.length-1];

        boolean found = false;
        GridPane foundMenu = new GridPane();
        for (Node n:orderDisplayPane.getChildren()) {
            GridPane item = (GridPane) n;

            String[] itemNameTmp = String.valueOf(item.getChildren().get(1)).split("'");
            String itemName = itemNameTmp[itemNameTmp.length-1];

            if(itemName.equals(menuName)){
                found = true;
                foundMenu = item;
                break;
            }
        }

        //menu already existed in order list
        if(found){
            Label qntLabel = (Label)foundMenu.getChildren().get(4);
            int qnt = Integer.valueOf(qntLabel.getText());
            if(qnt < 10){
                qntLabel.setText(String.valueOf(qnt+1));
                String[] priceString = String.valueOf(foundMenu.getChildren().get(2)).split("'");
                String priceTmp = priceString[priceString.length-1];
                priceTmp = priceTmp.replace(",","");
                int price = Integer.valueOf(priceTmp.split(" ")[0]);
                price += price/qnt;
                Label priceText = (Label)foundMenu.getChildren().get(2);
                priceText.setText(NumberFormat.getNumberInstance(Locale.US).format(price)+" Baht");
            }
        }

        //menu not found in order list
        else{
            //create order and add to right pane
            GridPane order = orderBuilder.createOrder(menu);

            //set action on del button
            order.getChildren().get(6).setOnMouseClicked(this::orderDelEvent);

            //-----------------------------------------//
            //set action on plus button
            order.getChildren().get(5).setOnMouseClicked(this::plusEvent);
            //set action on minus button
            order.getChildren().get(3).setOnMouseClicked(this::minusEvent);

            orderDisplayPane.getChildren().add(order);
        }
        int sum = sumPriceDisplay();
        totalPrice.setText(NumberFormat.getNumberInstance(Locale.US).format(sum)+" Baht");
        orderDisplay();
    }

    @FXML private void orderDelEvent(MouseEvent event){
        //set action on del button
        Button delBtn = (Button) event.getSource();
        orderDisplayPane.getChildren().remove(delBtn.getParent());
        orderDisplay();
        int sum = sumPriceDisplay();
        totalPrice.setText(NumberFormat.getNumberInstance(Locale.US).format(sum)+" Baht");

    }

    @FXML private void plusEvent(MouseEvent event){
        Button btn = (Button)event.getSource();
        GridPane menu = (GridPane)btn.getParent();
        Label qntLabel = (Label)menu.getChildren().get(4);
        String qnt = String.valueOf(qntLabel).split("'")[1];
        int quantity = Integer.valueOf(qnt);
        if(quantity < 10){
            qntLabel.setText(String.valueOf(quantity+1));
            String[] priceString = String.valueOf(menu.getChildren().get(2)).split("'");
            String priceTmp = priceString[priceString.length-1];
            priceTmp = priceTmp.replace(",","");
            int price = Integer.valueOf(priceTmp.split(" ")[0]);
            price += price/quantity;
            Label priceText = (Label)menu.getChildren().get(2);
            priceText.setText(NumberFormat.getNumberInstance(Locale.US).format(price)+" Baht");
            int sum = sumPriceDisplay();
            totalPrice.setText(NumberFormat.getNumberInstance(Locale.US).format(sum)+" Baht");
        }

    }

    @FXML private void minusEvent(MouseEvent event){
        Button btn = (Button)event.getSource();
        GridPane menu = (GridPane)btn.getParent();
        Label qntLabel = (Label)menu.getChildren().get(4);
        String qnt = String.valueOf(qntLabel).split("'")[1];
        int quantity = Integer.valueOf(qnt);
        if(quantity > 1){
            qntLabel.setText(String.valueOf(quantity-1));
            String[] priceString = String.valueOf(menu.getChildren().get(2)).split("'");
            String priceTmp = priceString[priceString.length-1];
            priceTmp = priceTmp.replace(",","");
            int price = Integer.valueOf(priceTmp.split(" ")[0]);
            price -= price/quantity;
            Label priceText = (Label)menu.getChildren().get(2);
            priceText.setText(NumberFormat.getNumberInstance(Locale.US).format(price)+" Baht");
            int sum = sumPriceDisplay();
            totalPrice.setText(NumberFormat.getNumberInstance(Locale.US).format(sum)+" Baht");
        }
    }

    @FXML private void orderDisplay(){
        int yLayout = 0;
        for (Node n:orderDisplayPane.getChildren()) {
            GridPane o = (GridPane) n;
            o.setLayoutY(yLayout);
            yLayout += 195;
        }
    }

    @FXML private int sumPriceDisplay(){
        int sum = 0;
        for (Node n:orderDisplayPane.getChildren()) {
            GridPane p = (GridPane) n;
            Label priceLabel = (Label)p.getChildren().get(2);
            String priceString = String.valueOf(priceLabel).split("'")[1].split(" ")[0];
            priceString = priceString.replace(",","");
            sum += Integer.parseInt(priceString);
        }
        return sum;
    }


}
