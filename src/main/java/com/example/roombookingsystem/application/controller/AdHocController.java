package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.application.FxmlView;
import com.example.roombookingsystem.application.SceneSwitcher;
import com.example.roombookingsystem.foundation.AvailableTimes;
import com.example.roombookingsystem.foundation.Booking;
import com.example.roombookingsystem.persistence.CrudDAO.bookingDAOImpl;
import com.example.roombookingsystem.persistence.StoredProcedures.spBooking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.awt.print.Book;
import java.io.IOException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
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
    TableView<AvailableTimes> RoomsTableView;
    @FXML
    TableColumn<AvailableTimes, String> roomNameColumn;
    @FXML
    TableColumn<AvailableTimes, String> timeStartColumn;
    @FXML
    TableColumn<AvailableTimes, String> timeEndColumn;
    @FXML
    TableColumn<AvailableTimes, String> roomSizeColumn;
    @FXML
    TableColumn<AvailableTimes, String> errorsColumn;
    @FXML
    TableColumn<AvailableTimes, String> actionColumn;

    ObservableList<AvailableTimes> data;
    public static AvailableTimes selectedRoom;

    public void initialize() {
        spBooking Bookings = new spBooking();
        Date date = new Date(System.currentTimeMillis());
        System.out.println(date);
        data = FXCollections.observableArrayList(Bookings.getAvailableTimesFilter(
                date,
                null,
                null,
                0,
                false,
                false,
                false,
                false,
                0
        ));
        DatoLabel.setText(java.time.LocalDate.now().toString());
        ClockLabel.setText(java.time.LocalTime.now().toString());

        filterMenu.setVisible(false);
        filterButton.setLayoutY(370);
        RoomsTableView.setPrefHeight(340);

        roomNameColumn.setCellValueFactory(new PropertyValueFactory<AvailableTimes, String>("roomName"));
        timeStartColumn.setCellValueFactory(new PropertyValueFactory<AvailableTimes, String>("timeStart"));
        timeEndColumn.setCellValueFactory(new PropertyValueFactory<AvailableTimes, String>("timeEnd"));
        roomSizeColumn.setCellValueFactory(new PropertyValueFactory<AvailableTimes, String>("roomSize"));
        errorsColumn.setCellValueFactory(new PropertyValueFactory<AvailableTimes, String>("errorsText"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<AvailableTimes, String>("actionText"));

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
        LokaleFilter.getItems().clear();
        LokaleFilter.getItems().add(new MenuItem("Alle Lokaler"));
        data.stream()
                .map(AvailableTimes::getRoomName)
                .distinct()
                .forEach(lokale -> {
                    MenuItem menuItem = new MenuItem(lokale);
                    menuItem.setOnAction(event -> filterByRoom(lokale));
                    LokaleFilter.getItems().add(menuItem);
                });
        LokaleFilter.getItems().get(0).setOnAction(event -> RoomsTableView.setItems(data));  // all
    }
    private void filterByRoom(String roomNumber) {
        ObservableList<AvailableTimes> filteredData = data.stream()
                .filter(room -> room.getRoomName().equals(roomNumber))
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
        ObservableList<AvailableTimes> filteredData = data.stream()
                .filter(room -> room.getTimeStart().equals(time))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        RoomsTableView.setItems(filteredData);
    }
}