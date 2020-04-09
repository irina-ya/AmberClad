import com.jfoenix.controls.JFXButton;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        primaryStage.setTitle("Bike Shop Assistant");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
    }




    @FXML public void custDirectory_click() throws IOException {
        Stage orderStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Screen_CustomerDirectory.fxml"));
        orderStage.setTitle("Customer Directory");
        orderStage.setScene(new Scene(root, 950, 768));
        orderStage.show();}




    public static void main(String[] args) {
        launch(args);
    }
}

