package com.example.roombookingsystem.application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import com.example.roombookingsystem.foundation.ObservableRoomTest;

public class AdHocController {

    @FXML
    Pane MainPane;
    @FXML
    Pane filterMenu;
    @FXML
    Button filterButton;
    @FXML
    Label DatoLabel;
    @FXML
    Label ClockLabel;
    @FXML
    TableView<ObservableRoomTest> RoomsTableView;
    @FXML
    TableColumn<ObservableRoomTest, String> LokaleColumn;
    @FXML
    TableColumn<ObservableRoomTest, String> TidColumn;
    @FXML
    TableColumn<ObservableRoomTest, String> FejlColumn;
    @FXML
    TableColumn<ObservableRoomTest, String> HandlingColumn;

    ObservableList<ObservableRoomTest> data = FXCollections.observableArrayList(
             new ObservableRoomTest("306", "09:45-12:00", "ingen fejl", "book"),
             new ObservableRoomTest("306", "13:15-14:00", "ingen fejl", "book")
    );
    public void initialize() {
        DatoLabel.setText(java.time.LocalDate.now().toString());
        ClockLabel.setText(java.time.LocalTime.now().toString());

        filterMenu.setVisible(false);
        filterButton.setLayoutY(370);
        RoomsTableView.setPrefHeight(340);

        LokaleColumn.setCellValueFactory(new PropertyValueFactory<ObservableRoomTest, String>("lokaleNavn"));
        TidColumn.setCellValueFactory(new PropertyValueFactory<ObservableRoomTest, String>("tid"));
        FejlColumn.setCellValueFactory(new PropertyValueFactory<ObservableRoomTest, String>("fejl"));
        HandlingColumn.setCellValueFactory(new PropertyValueFactory<ObservableRoomTest, String>("handling"));

        RoomsTableView.setItems(data);
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