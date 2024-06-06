package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.foundation.Room;
import com.example.roombookingsystem.persistence.GenericQuerries.DBRooms;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EmployeeBookingController {
    @FXML
    private ChoiceBox<Room> choiceRoom;
    @FXML
    private Label EnkeltBookingLabel;
    @FXML
    private Label BulkLabel;
    @FXML
    private DatePicker dpStart;
    @FXML
    private DatePicker dpEnd;

    @FXML
    public void initialize() {
        toggleBoldText(EnkeltBookingLabel, BulkLabel);
        EnkeltBookingLabel.setUnderline(true);

        EnkeltBookingLabel.setOnMouseClicked(event -> {
            toggleBoldText(EnkeltBookingLabel, BulkLabel);
            EnkeltBookingLabel.setUnderline(true);
            BulkLabel.setUnderline(false);
        });
        BulkLabel.setOnMouseClicked(event -> {
            toggleBoldText(BulkLabel, EnkeltBookingLabel);
            BulkLabel.setUnderline(true);
            EnkeltBookingLabel.setUnderline(false);
        });

        DBRooms dbRooms = new DBRooms();
        for (Room room : dbRooms.getAllRooms()) {
            choiceRoom.getItems().add(room);
        }

        dpStart.valueProperty().addListener((ov, oldValue, newValue)->{

        });
    }

    private void toggleBoldText(Label labelToBold, Label labelToNormal) {
        Font boldFont = Font.font(labelToBold.getFont().getFamily(), FontWeight.BOLD, labelToBold.getFont().getSize());
        labelToBold.setFont(boldFont);

        Font normalFont = Font.font(labelToNormal.getFont().getFamily(), FontWeight.NORMAL, labelToNormal.getFont().getSize());
        labelToNormal.setFont(normalFont);
    }
    //private void
}
