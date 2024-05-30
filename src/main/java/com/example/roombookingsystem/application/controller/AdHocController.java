package com.example.roombookingsystem.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class AdHocController {


    @FXML
    Pane MainPane;
    @FXML
    Pane filterMenu;
    @FXML
    Button filterButton;
    @FXML
    TableView RoomsTableView;

    public void initialize() {
        filterMenu.setVisible(false);
        filterButton.setLayoutY(370);
        RoomsTableView.setPrefHeight(340);

        filterButton.setOnMouseClicked(event -> {
            filterMenu.setVisible(!filterMenu.isVisible());
            if (filterMenu.isVisible()) {
                filterButton.setLayoutY(270);
                RoomsTableView.setPrefHeight(240);
            } else {
                filterButton.setLayoutY(370);
                RoomsTableView.setPrefHeight(340);
            }
        });
    }
}
