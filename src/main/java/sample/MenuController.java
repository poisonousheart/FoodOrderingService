package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import net.rgielen.fxweaver.core.FxmlView;

import java.io.*;

public class MenuController {

    Timeline timeline = new Timeline();
    private MenuBuilder menuBuilder;
    @FXML Label categoryName;
    @FXML Button confirmButton, backButton, orderButton,b1,b2,b3,b4,b5,b6,riceCate,noodleCate,hotpotCate,appetizerCate,sushiCate,beverageCate;
    @FXML AnchorPane menuPage, confirmPage, menuDisplayPane;
    @FXML ScrollPane menuDisplayScroll;

    public void initialize() throws IOException{
        confirmPage.setVisible(false);
        menuPage.setDisable(false);

        categoryName.setText("Rices");
        //categoryName.setAlignment(Pos.CENTER);
        menuBuilder = new MenuBuilder();
        int xLayout = 20, yLayout = 0, maxPerLine = 3;

        menuDisplayPane.prefHeightProperty().bind(menuDisplayScroll.maxHeightProperty());
        menuDisplayPane.prefWidthProperty().bind(menuDisplayScroll.maxWidthProperty());

        GridPane menu = new GridPane();
        for (int i = 0; i < 12; i++) {
            if(i % 2 == 0){
                menu = menuBuilder.getMenu("http://64856cb68ed9.ngrok.io/api/menus/search/A1");
            }
            else{
                menu = menuBuilder.getMenu("http://64856cb68ed9.ngrok.io/api/menus/search/A2");
            }
            //left placement
            if(i % maxPerLine == 0){
                xLayout = 20;
            }

            //mimddle placement
            else if(i % maxPerLine == 1){
                xLayout += 490;
            }

            //right placement
            else if(i % maxPerLine == 2){
                xLayout += 490;
            }
            menu.setLayoutX(xLayout);
            menu.setLayoutY(yLayout);
            menuDisplayPane.getChildren().add(menu);
            yLayout += i % maxPerLine==2?350:0;
        }

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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("OrderStatusController.fxml"));
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    @FXML public void categoryButtonClick(MouseEvent event){
        Button button = (Button) event.getSource();
        String id = button.getId();
            if(id.equals("b1")) categoryName.setText("Rices");
            else if (id.equals("b2")) categoryName.setText("Noodles");
            else if (id.equals("b3")) categoryName.setText("Hot Pots");
            else if (id.equals("b4")) categoryName.setText("Appetizers");
            else if (id.equals("b5")) categoryName.setText("Sushi");
            else if (id.equals("b6"))categoryName.setText("Beverages");
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
}
