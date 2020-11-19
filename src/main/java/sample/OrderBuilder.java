package sample;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class OrderBuilder {

    public GridPane createOrder(GridPane menu){
//        System.out.println(menu.getChildren().get(0));
//        System.out.println(menu.getChildren().get(1));
//        System.out.println(menu.getChildren().get(2));

        //create gridpane
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(0);
        gridPane.setLayoutY(20);
        gridPane.setPrefHeight(150);
        gridPane.setPrefWidth(340);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setId("orderPane");

        //set grid column
        ColumnConstraints col = new ColumnConstraints();
        col.setHgrow(Priority.SOMETIMES);
        col.setMinWidth(10);
        col.setPrefWidth(100);
        gridPane.getColumnConstraints().add(col);

        //set grid row
        RowConstraints row1 = new RowConstraints();
        row1.setMaxHeight(225);
        row1.setMinHeight(10);
        row1.setVgrow(Priority.SOMETIMES);

        RowConstraints row2 = new RowConstraints();
        row2.setMaxHeight(93.33333587646484);
        row2.setMinHeight(10);
        row2.setPrefHeight(48.66667175292969);
        row2.setVgrow(Priority.SOMETIMES);

        RowConstraints row3 = new RowConstraints();
        row3.setMaxHeight(54.333343505859375);
        row3.setMinHeight(10);
        row3.setPrefHeight(49.00001525878906);
        row3.setVgrow(Priority.SOMETIMES);

        RowConstraints row4 = new RowConstraints();
        row4.setMaxHeight(54.333343505859375);
        row4.setMinHeight(10);
        row4.setPrefHeight(49.00001525878906);
        row4.setVgrow(Priority.SOMETIMES);

        gridPane.getRowConstraints().addAll(row1, row2, row3, row4);

        //add content for gridpane
        //set menu image row 1
        ImageView menuImg = (ImageView) menu.getChildren().get(0);
        ImageView orderImg = new ImageView();
        orderImg.setImage(menuImg.getImage());
        orderImg.setFitHeight(150);
        orderImg.setFitWidth(250);
        orderImg.setPreserveRatio(true);
        orderImg.setPickOnBounds(true);
        gridPane.addRow(0,orderImg);

        //set menu name row 2
        Label menuName = new Label();
        menuName.setPrefHeight(150);
        menuName.setPrefWidth(325);

        String[] tmp = String.valueOf(menu.getChildren().get(1)).split("'");
        String name = tmp[tmp.length-1];
        menuName.setText(name);

        menuName.setPadding(new Insets(0,10,0,10));
        menuName.setId("ordermenu");
        gridPane.addRow(1, menuName);

        //set menu price row 3
        Label menuPrice = new Label();
        menuPrice.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        menuPrice.setPrefHeight(150);
        menuPrice.setPrefWidth(325);

        tmp = String.valueOf(menu.getChildren().get(2)).split("'");
        String price = tmp[tmp.length-1];
        menuPrice.setText(price);

        menuPrice.setPadding(new Insets(0,10,0,10));
        menuPrice.setId("orderprice");
        gridPane.addRow(2, menuPrice);

        //set button row 4
        Button minBtn = new Button();
        Button plusBtn = new Button();
        Button delBtn = new Button();
        Label qnt = new Label();

        minBtn.setText("-");
        qnt.setText("1");
        plusBtn.setText("+");
        delBtn.setText("Del");

        //set action on minus button
        minBtn.setOnMouseClicked(event -> {
            int quantity = Integer.valueOf(qnt.getText());
            if(quantity > 1){
                qnt.setText(String.valueOf(quantity-1));
            }
        });

        //set action on plus button
        plusBtn.setOnMouseClicked(event -> {
            int quantity = Integer.valueOf(qnt.getText());
            if(quantity < 10){
                qnt.setText(String.valueOf(quantity+1));

            }
        });

        gridPane.addRow(3, minBtn, qnt, plusBtn, delBtn);


        return gridPane;
    }
}
