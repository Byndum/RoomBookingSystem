package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.application.FxmlView;
import com.example.roombookingsystem.application.SceneSwitcher;
import com.example.roombookingsystem.foundation.AdHoc;
import com.example.roombookingsystem.foundation.AvailableTimes;
import com.example.roombookingsystem.foundation.Room;
import com.example.roombookingsystem.persistence.GenericQuerries.DBRooms;
import com.example.roombookingsystem.persistence.StoredProcedures.spBooking;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.Date;
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
    MenuButton RoomFilter;
    @FXML
    MenuButton TimeFilter;
    @FXML
    TableView<AdHoc> RoomsTableView;
    @FXML
    TableColumn<AdHoc, String> roomNameColumn;
    @FXML
    TableColumn<AdHoc, String> timeStartColumn;
    @FXML
    TableColumn<AdHoc, String> timeEndColumn;
    @FXML
    TableColumn<AdHoc, String> roomSizeColumn;
    @FXML
    TableColumn<AdHoc, String> errorsColumn;
    @FXML
    TableColumn<AdHoc, String> actionColumn;

    ObservableList<AdHoc> data;
    public static AdHoc selectedRoom;


    public void initialize() {
        spBooking Bookings = new spBooking();
        DBRooms rooms = new DBRooms();
        Date date = new Date(System.currentTimeMillis());
        System.out.println(date);
        data = FXCollections.observableArrayList(AdHoc.getRoomArray(Bookings.getAvailableTimesFilter(
                date,
                null,
                null,
                0,
                false,
                false,
                false,
                false,
                0
        ), rooms.getAllRooms()));

        DatoLabel.setText(java.time.LocalDate.now().toString());
        ClockLabel.setText(java.time.LocalTime.now().toString());

        filterMenu.setVisible(false);
        filterButton.setLayoutY(370);
        RoomsTableView.setPrefHeight(340);

        roomNameColumn.setCellValueFactory(new PropertyValueFactory<AdHoc, String>("roomName"));
        timeStartColumn.setCellValueFactory(new PropertyValueFactory<AdHoc, String>("timeStart"));
        timeEndColumn.setCellValueFactory(new PropertyValueFactory<AdHoc, String>("timeEnd"));
        roomSizeColumn.setCellValueFactory(new PropertyValueFactory<AdHoc, String>("roomSize"));
        errorsColumn.setCellValueFactory(cellData -> {
            AdHoc adHoc = cellData.getValue();
            return new SimpleStringProperty(adHoc.hasErrors() ? "ⓘ" : "");
        });
        actionColumn.setCellValueFactory(new PropertyValueFactory<AdHoc, String>("actionText"));



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
        RoomsTableView.setOnMouseClicked(event -> {
            try {
                selectedRoom = RoomsTableView.getSelectionModel().getSelectedItem();
                if (selectedRoom != null) {
                    RoomDetailsController controller = SceneSwitcher.getInstance().createDetailsPopUp(FxmlView.ROOMDETAILS);
                    controller.setRoomDetails(selectedRoom);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void populateLokaleFilter() {
        RoomFilter.getItems().clear();
        RoomFilter.getItems().add(new MenuItem("Alle Lokaler"));
        data.stream()
                .map(AdHoc::getRoomName)
                .distinct()
                .forEach(lokale -> {
                    MenuItem menuItem = new MenuItem(lokale);
                    menuItem.setOnAction(event -> filterByRoom(lokale));
                    RoomFilter.getItems().add(menuItem);
                });
        RoomFilter.getItems().get(0).setOnAction(event -> RoomsTableView.setItems(data));  // all
    }
    private void filterByRoom(String roomNumber) {
        ObservableList<AdHoc> filteredData = data.stream()
                .filter(room -> room.getRoomName().equals(roomNumber))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        RoomsTableView.setItems(filteredData);
    }
    private void populateTidFilter() {
        TimeFilter.getItems().forEach(menuItem -> {
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
        ObservableList<AdHoc> filteredData = data.stream()
                .filter(room -> room.getTimeStart().equals(time))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        RoomsTableView.setItems(filteredData);
    }
}