package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(HelloApplication.class.getResource("rootLayout.fxml"));
        BorderPane rootLayout = (BorderPane) fxmlLoader.load();

        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        stage.show();

        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(HelloApplication.class.getResource("employee_view.fxml"));
        AnchorPane view = (AnchorPane) mainLoader.load();

        rootLayout.setCenter(view);
    }

    public static void main(String[] args) {
        launch(args);
    }
}