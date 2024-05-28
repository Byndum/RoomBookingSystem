package com.example.roombookingsystem.application.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class HomeController {
    @FXML
    private StackPane menuHome;
    @FXML
    private StackPane menuBook;
    @FXML
    private StackPane menuEdit;
    @FXML
    private StackPane menuHistory;
    @FXML
    private StackPane menuRegisterRoom;
    @FXML
    private StackPane menuRegisterError;
    @FXML
    private StackPane menuAllErrors;

    @FXML
    public void initialize() {
        menuHome.setStyle("-fx-background-color: #00adef");
        //menuHome.setStyle("-fx-background-color: linear-gradient(to right, #00adef, #f4f4f4)");
        menuBook.setStyle("-fx-background-color: #00adef");
        menuEdit.setStyle("-fx-background-color: #00adef");
        menuHistory.setStyle("-fx-background-color: #00adef");
        menuRegisterRoom.setStyle("-fx-background-color: #00adef");
        menuRegisterError.setStyle("-fx-background-color: #00adef");
        menuAllErrors.setStyle("-fx-background-color: #00adef");

        menuHome.setOnMouseClicked(mouseEvent -> {
            System.out.println("pane has been clicked!");
        });

        menuHome.setOnMouseEntered(mouseEvent -> {
            System.out.println("hover");
            menuHome.setStyle("-fx-background-color: #00bfff");
        });

        menuHome.setOnMouseExited(mouseEvent -> {
            System.out.println("no longer hover");
            menuHome.setStyle("-fx-background-color: #00adef");
            //menuHome.setStyle("-fx-background-color: #fcfcfc");
        });
    }

    public void menuHomeClick(MouseEvent mouseEvent) {
        System.out.println("test");
    }
}
