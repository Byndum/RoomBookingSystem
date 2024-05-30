package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.foundation.ObservableBooking;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class AdHocController {

    @FXML
    Pane MainPane;
    @FXML
    Pane filterMenu;
    @FXML
    Button filterButton;
    @FXML
    TableView<String> RoomsTableView;




    public void initialize() {

        filterMenu.setVisible(false);
        filterButton.setLayoutY(370);
        RoomsTableView.setPrefHeight(340);


        filterButton.setOnMouseClicked(event -> { // test
            boolean isVisible = !filterMenu.isVisible();
            filterMenu.setVisible(isVisible);
            if (isVisible) {
                filterButton.setLayoutY(270);
                RoomsTableView.setPrefHeight(270);
            } else {
                filterButton.setLayoutY(370);
                RoomsTableView.setPrefHeight(340);
            }
        });
    }
}
