package sample;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.io.IOException;

public class MenuBuilder {


    private MenuSlicer menuSlicer;

    public MenuBuilder() {
        menuSlicer = new MenuSlicer();
    }

    public GridPane getMenu(String url) throws IOException {
        //create gridpane
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(40);
        gridPane.setLayoutY(20);
        gridPane.setPrefHeight(325);
        gridPane.setPrefWidth(325);

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
        gridPane.getRowConstraints().addAll(row1, row2, row3);

        //add content for gridpane
        //set menu image
        menuSlicer.setData(url);
        Image image = menuSlicer.getImage();
        ImageView menuImg = new ImageView();
        menuImg.setFitHeight(225);
        menuImg.setFitWidth(325);
        menuImg.setPickOnBounds(true);
        menuImg.setImage(image);
        gridPane.addRow(0,menuImg);

        //set menu name
        Label menuName = new Label();
        menuName.setPrefHeight(50);
        menuName.setPrefWidth(325);
        menuName.setText(menuSlicer.getMenuName());
        menuName.setPadding(new Insets(0,10,0,10));
        menuName.setId("namemenu");
        gridPane.addRow(1, menuName);

        //set menu price
        Label menuPrice = new Label();
        menuPrice.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        menuPrice.setPrefHeight(50);
        menuPrice.setPrefWidth(325);
        menuPrice.setText(menuSlicer.getPrice()+" Baht");
        menuPrice.setPadding(new Insets(0,10,0,10));
        menuPrice.setId("pricemenu");
        gridPane.addRow(2, menuPrice);

        return gridPane;
    }
}
