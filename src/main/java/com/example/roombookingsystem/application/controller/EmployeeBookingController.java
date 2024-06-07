package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.foundation.AvailableTimes;
import com.example.roombookingsystem.foundation.Room;
import com.example.roombookingsystem.persistence.GenericQuerries.DBRooms;
import com.example.roombookingsystem.persistence.StoredProcedures.spBooking;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;

public class EmployeeBookingController {
    @FXML
    private ChoiceBox<Room> choiceRoom;
    @FXML
    private Label EnkeltBookingLabel;
    @FXML
    private Label BulkLabel;
    @FXML
    private Label labelEndDate;
    @FXML
    private DatePicker dpStart;
    @FXML
    private DatePicker dpEnd;
    @FXML
    private ListView listViewAvailableTimes;

    private Date dStart;
    private Date dEnd;
    private spBooking DBSPBooking = new spBooking();
    private ArrayList<AvailableTimes> desiredBookings = new ArrayList<>();

    @FXML
    public void initialize() {
        toggleBoldText(EnkeltBookingLabel, BulkLabel);
        EnkeltBookingLabel.setUnderline(true);

        EnkeltBookingLabel.setOnMouseClicked(event -> {
            dpEnd.setDisable(true);
            dpEnd.setVisible(false);
            labelEndDate.setVisible(false);
            toggleBoldText(EnkeltBookingLabel, BulkLabel);
            EnkeltBookingLabel.setUnderline(true);
            BulkLabel.setUnderline(false);
        });
        BulkLabel.setOnMouseClicked(event -> {
            dpEnd.setDisable(false);
            dpEnd.setVisible(true);
            labelEndDate.setVisible(true);
            toggleBoldText(BulkLabel, EnkeltBookingLabel);
            BulkLabel.setUnderline(true);
            EnkeltBookingLabel.setUnderline(false);
        });

        DBRooms dbRooms = new DBRooms();
        for (Room room : dbRooms.getAllRooms()) {
            choiceRoom.getItems().add(room);
        }

        dpStart.valueProperty().addListener((ov, oldValue, newValue) -> ChangeStartDate(Date.valueOf(newValue),newValue));
        dpEnd.valueProperty().addListener((ov, oldValue, newValue) -> ChangeEndDate(Date.valueOf(newValue),newValue));
    }

    private void toggleBoldText(Label labelToBold, Label labelToNormal) {
        Font boldFont = Font.font(labelToBold.getFont().getFamily(), FontWeight.BOLD, labelToBold.getFont().getSize());
        labelToBold.setFont(boldFont);

        Font normalFont = Font.font(labelToNormal.getFont().getFamily(), FontWeight.NORMAL, labelToNormal.getFont().getSize());
        labelToNormal.setFont(normalFont);
    }

    private void ChangeStartDate(Date newDate, LocalDate newLocalDate) {
        dStart = newDate;
        if (dEnd == null || dEnd.before(dStart)) {
            dEnd = dStart;
        }

        populateAvailable();
    }

    private void ChangeEndDate(Date newDate, LocalDate newLocalDate) {
        dEnd = newDate;
        if (dStart == null) {
            dStart = Date.valueOf(LocalDate.now());
        }

        populateAvailable();
    }

    private void populateAvailable() {
        listViewAvailableTimes.getItems().clear();
        ArrayList<AvailableTimes> availableTimesArrayList = new ArrayList<>();
        for (AvailableTimes availableTimes : DBSPBooking.getAvailableTimesFilter(
                dStart,
                null,
                null,
                0,
                false,
                false,
                false,
                false,
                choiceRoom.getSelectionModel().getSelectedItem().getRoomID()
        )) {
            availableTimesArrayList.add(availableTimes);
        }
        for (AvailableTimes at : availableTimesArrayList) {
            listViewAvailableTimes.getItems().add(at);
        }
    }
}
