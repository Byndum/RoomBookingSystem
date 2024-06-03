package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.application.FxmlView;
import com.example.roombookingsystem.application.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class HomeEmployeeController {
    //region FXML declarations
    @FXML
    private StackPane menuHome;
    @FXML
    private StackPane menuBook;
    @FXML
    private StackPane menuEdit;
    @FXML
    private StackPane menuHistory;
    @FXML
    private StackPane menuMyErrors;
    //endregion

    @FXML
    public void initialize() {
        menuHome.setStyle("-fx-background-color: #00adef");
        menuBook.setStyle("-fx-background-color: #00adef");
        menuEdit.setStyle("-fx-background-color: #00adef");
        menuHistory.setStyle("-fx-background-color: #00adef");
        menuMyErrors.setStyle("-fx-background-color: #00adef");

        //region MouseEntered + MouseExited
        menuHome.setOnMouseEntered(mouseEvent -> {
            System.out.println("hover");
            menuHome.setStyle("-fx-background-color: #00bfff");
        });
        menuHome.setOnMouseExited(mouseEvent -> {
            System.out.println("no longer hover");
            menuHome.setStyle("-fx-background-color: #00adef");
            //menuHome.setStyle("-fx-background-color: #fcfcfc");
        });
        menuBook.setOnMouseEntered(mouseEvent -> {
            System.out.println("hover");
            menuBook.setStyle("-fx-background-color: #00bfff");
        });
        menuBook.setOnMouseExited(mouseEvent -> {
            System.out.println("no longer hover");
            menuBook.setStyle("-fx-background-color: #00adef");
            //menuHome.setStyle("-fx-background-color: #fcfcfc");
        });
        menuEdit.setOnMouseEntered(mouseEvent -> {
            System.out.println("hover");
            menuEdit.setStyle("-fx-background-color: #00bfff");
        });
        menuEdit.setOnMouseExited(mouseEvent -> {
            System.out.println("no longer hover");
            menuEdit.setStyle("-fx-background-color: #00adef");
            //menuHome.setStyle("-fx-background-color: #fcfcfc");
        });
        menuHistory.setOnMouseEntered(mouseEvent -> {
            System.out.println("hover");
            menuHistory.setStyle("-fx-background-color: #00bfff");
        });
        menuHistory.setOnMouseExited(mouseEvent -> {
            System.out.println("no longer hover");
            menuHistory.setStyle("-fx-background-color: #00adef");
            //menuHome.setStyle("-fx-background-color: #fcfcfc");
        });
        menuMyErrors.setOnMouseEntered(mouseEvent -> {
            System.out.println("hover");
            menuMyErrors.setStyle("-fx-background-color: #00bfff");
        });
        menuMyErrors.setOnMouseExited(mouseEvent -> {
            System.out.println("no longer hover");
            menuMyErrors.setStyle("-fx-background-color: #00adef");
            //menuHome.setStyle("-fx-background-color: #fcfcfc");
        });
        //endregion
    }
    public void menuHomeClick(MouseEvent mouseEvent) throws IOException {
        SceneSwitcher.getInstance().switchScene(FxmlView.HOMEEMPLOYEE);
    }

    public void btnBookClick(MouseEvent mouseEvent) throws IOException {
        SceneSwitcher.getInstance().createPopUp(FxmlView.EMPLOYEEBOOKING);
    }

    public void btnEditClick(MouseEvent mouseEvent) throws IOException {
        SceneSwitcher.getInstance().switchScene(FxmlView.EDITEMPLOYEE);
    }
}
