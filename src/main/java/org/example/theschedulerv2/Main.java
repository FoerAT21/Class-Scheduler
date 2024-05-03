package org.example.theschedulerv2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    //Opens the mainWindow
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 500);
        scene.getStylesheets().add(Main.class.getResource("lightmode.css").toExternalForm());
        stage.setTitle("Main Window!");
        stage.setScene(scene);
        stage.show();
    }
}
