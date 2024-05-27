package com.example.roombookingsystem.application.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class HomeController {
    @FXML
    private StackPane menuHome;

    @FXML
    public void initialize() {
        menuHome.setOnMouseClicked(mouseEvent -> {
            System.out.println("pane has been clicked!");
        });

        menuHome.setOnMouseEntered(mouseEvent -> {
            System.out.println("hover");
            menuHome.setStyle("-fx-background-color: #000");
        });

        menuHome.setOnMouseExited(mouseEvent -> {
            System.out.println("no longer hover");
            menuHome.setStyle("-fx-background-color: #fff");
        });
    }

    public void menuHomeClick(MouseEvent mouseEvent) {
        System.out.println("test");
    }
}
