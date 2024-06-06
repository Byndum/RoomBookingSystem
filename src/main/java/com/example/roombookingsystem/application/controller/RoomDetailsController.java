package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.foundation.AvailableTimes;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.awt.event.ActionEvent;

public class RoomDetailsController {

    @FXML
    Label selectedRoomName;
    @FXML
    Label selectedRoomCapacity;
    @FXML
    Label selectedRoomProjector;
    @FXML
    Label selectedRoomSpeakers;
    @FXML
    Label selectedRoomPower;
    @FXML
    Label selectedRoomWBboard;
    @FXML
    Label selectedRoomErrors;

    public void setRoomDetails(AvailableTimes room){
        selectedRoomName.setText(room.getRoomName());
        selectedRoomCapacity.setText("Størrelse: " + room.getRoomSize() + " pladser");

    }

    @FXML
    public void initialize() {
        selectedRoomName.setText("test");
//        Stage stage = (Stage) selectedRoomName.getScene().getWindow();
//        AvailableTimes aT = (AvailableTimes) stage.getUserData();
//        selectedRoomName.setText(aT.getRoomName());
    }
    @FXML
    public void btnBookClicked(javafx.event.ActionEvent actionEvent) {

    }
}
