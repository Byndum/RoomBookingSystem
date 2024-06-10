package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.application.FxmlView;
import com.example.roombookingsystem.application.SceneSwitcher;
import com.example.roombookingsystem.foundation.AdHoc;
import com.example.roombookingsystem.persistence.GenericQuerries.DBRooms;
import com.example.roombookingsystem.persistence.StoredProcedures.SPBooking;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    ChoiceBox RoomFilter;
    @FXML
    ChoiceBox TimeFilter;
    @FXML
    ChoiceBox SpaceFilter;
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
    @FXML
    CheckBox projektorCheck;
    @FXML
    CheckBox SpeakerCheck;
    @FXML
    CheckBox PowerCheck;
    @FXML
    CheckBox WBoardCheck;

    ObservableList<AdHoc> data;
    public static AdHoc selectedRoom;


    public void initialize() {
        SPBooking Bookings = new SPBooking();
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
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO,e ->
                ClockLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();

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
        populateRoomFilter();
        populateTimeFilter();

        RoomsTableView.setOnMouseClicked(event -> {
            try {
                selectedRoom = RoomsTableView.getSelectionModel().getSelectedItem();
                if (selectedRoom != null) {
                    RoomDetailsController controller = SceneSwitcher.getInstance().createDetailsPopUp(FxmlView.ROOMDETAILS,this);
                    controller.setRoomDetails(selectedRoom);
                    controller.setTimes(selectedRoom.getTimeStart(), selectedRoom.getTimeEnd());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void refreshTableview() {
        SPBooking Bookings = new SPBooking();
        DBRooms rooms = new DBRooms();
        Date date = new Date(System.currentTimeMillis());
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
        RoomsTableView.setItems(data);
    }

    private void populateRoomFilter() {
        RoomFilter.getItems().clear();
        RoomFilter.getItems().add("Alle Lokaler");

        // Collect distinct room names using a set
        Set<String> roomNames = new HashSet<>();
        for (AdHoc room : data) {
            roomNames.add(room.getRoomName());
        }

        RoomFilter.getItems().addAll(roomNames);

        // Add a listener to handle selection change
        RoomFilter.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.equals("Alle Lokaler")) {
                filterByRoom(newValue.toString());
            } else {
                RoomsTableView.setItems(data); // Show all rooms
            }
        });
    }
    private void filterByRoom(String roomNumber) {
        ObservableList<AdHoc> filteredData = FXCollections.observableArrayList();
        for (AdHoc room : data) {
            if (room.getRoomName().equals(roomNumber)) {
                filteredData.add(room);
            }
        }
        RoomsTableView.setItems(filteredData);
    }
    private void populateTimeFilter() {
        TimeFilter.getItems().clear();
        Time tempTimeStart = new Time(8,0,0);
        Time tempTime = tempTimeStart;
        Time tempEndTime = new Time(16,0,0);


        while(tempTime.toLocalTime().isBefore(tempEndTime.toLocalTime())){
            TimeFilter.getItems().add(tempTime);
            tempTime = Time.valueOf(tempTime.toLocalTime().plusMinutes(15));
        }
        TimeFilter.getSelectionModel().select(tempTimeStart);
    }
    private void tidFilter(String time) {
        ObservableList<AdHoc> filteredData = FXCollections.observableArrayList();
        for (AdHoc room : data) {
            if (room.getTimeStart().toString().equals(time)) {
                filteredData.add(room);
            }
        }
        RoomsTableView.setItems(filteredData);
    }

    public void onConfirmButtonClick(ActionEvent actionEvent) {
        SPBooking Bookings = new SPBooking();
        DBRooms rooms = new DBRooms();
        Date date = new Date(System.currentTimeMillis());

        boolean filterByProjektor = projektorCheck.isSelected();
        boolean filterBySpeaker = SpeakerCheck.isSelected();
        boolean filterByPower = PowerCheck.isSelected();
        boolean filterByWboard = WBoardCheck.isSelected();
        Time filterByTime = (Time) TimeFilter.getSelectionModel().getSelectedItem();

        data = FXCollections.observableArrayList(AdHoc.getRoomArray(Bookings.getAvailableTimesFilter(
                date,
                filterByTime,
                null,
                0,
                filterByProjektor,
                filterBySpeaker,
                filterByPower,
                filterByWboard,
                0
        ), rooms.getAllRooms()));
        RoomsTableView.setItems(data);
    }
}