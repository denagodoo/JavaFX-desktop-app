package com.example.javafx.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class RootLayoutController {

    public void handleExit(ActionEvent event) {
        System.exit(0);
    }

    public void handleHelp(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Program information");
        alert.setHeaderText("it is just a test program in javaFX");
        alert.setContentText("a test");
        alert.show();
    }
}
