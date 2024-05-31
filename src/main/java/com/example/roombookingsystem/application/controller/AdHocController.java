package com.example.roombookingsystem.application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import com.example.roombookingsystem.foundation.ObservableRoomTest;

import java.util.stream.Collectors;

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
    MenuButton LokaleFilter;
    @FXML
    MenuButton TidFilter;
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
             new ObservableRoomTest("306", "13:15-14:00", "ingen fejl", "book"),
            new ObservableRoomTest("406", "13:15-14:00", "ingen fejl", "book")
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
        populateLokaleFilter();
        populateTidFilter();
    }
    private void populateLokaleFilter() {
        LokaleFilter.getItems().clear();
        LokaleFilter.getItems().add(new MenuItem("Alle Lokaler"));
        data.stream()
                .map(ObservableRoomTest::getLokaleNavn)
                .distinct()
                .forEach(lokale -> {
                    MenuItem menuItem = new MenuItem(lokale);
                    menuItem.setOnAction(event -> filterByRoom(lokale));
                    LokaleFilter.getItems().add(menuItem);
                });
        LokaleFilter.getItems().get(0).setOnAction(event -> RoomsTableView.setItems(data));  // all
    }
    private void filterByRoom(String roomNumber) {
        ObservableList<ObservableRoomTest> filteredData = data.stream()
                .filter(room -> room.getLokaleNavn().equals(roomNumber))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        RoomsTableView.setItems(filteredData);
    }
    private void populateTidFilter() {
        TidFilter.getItems().forEach(menuItem -> {
            menuItem.setOnAction(event -> {
                String time = menuItem.getText();
                if ("Alle tider".equals(time)) {
                    RoomsTableView.setItems(data);
                } else {
                    filterByTime(time);
                }
            });
        });
    }
    private void filterByTime(String time) {
        ObservableList<ObservableRoomTest> filteredData = data.stream()
                .filter(room -> room.getTid().startsWith(time))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        RoomsTableView.setItems(filteredData);
    }
}