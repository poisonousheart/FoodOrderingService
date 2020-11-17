package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("BookingController.fxml"));
        primaryStage.setTitle("Satoya");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
//        //DataSlicer Testing
//        OrderSlicer d = new OrderSlicer();
//        System.out.println("----Order---");
//        d.setData("{\"id\":1,\"menu_id\":\"A2\",\"table_number\":1,\"status\":\"complete\",\"created_at\":\"2020-11-02T23:29:39.000000Z\",\"updated_at\":\"2020-11-02T23:29:44.000000Z\",\"deleted_at\":null}");
//        d.debug();
//
//        //Menu Getting Testing
//        MenuSlicer m = new MenuSlicer();
//        System.out.println("----Menu------");
//        m.setData("{\"id\":1,\"menu_id\":\"A1\",\"menu_status\":0,\"menu_name\":\"salmon sushi\",\"price\":50,\"recipe\":\"\",\"created_at\":\"2020-10-30T08:18:43.000000Z\",\"updated_at\":\"2020-11-03T03:07:21.000000Z\",\"deleted_at\":null}");
//        m.debug();
        launch(args);

    }
}
