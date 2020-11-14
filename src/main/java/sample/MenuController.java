package sample;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MenuController {

    @FXML ImageView Icon;

    public void initialize(){

    }

    @FXML public void exitButton(MouseEvent event) throws IOException {
        System.out.println("Exit Program");

    }

}
