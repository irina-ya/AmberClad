package BikeShopApp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    //DON'T PUT ANYTHING ELSE HERE <3
    //Controller methods should go in designated controller file :)
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Sidebar.fxml"));
        primaryStage.setTitle("Bike Shop Assistant");
        primaryStage.setScene(new Scene(root, 1145, 768));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

