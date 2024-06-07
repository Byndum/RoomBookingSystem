package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.foundation.AdHoc;
import com.example.roombookingsystem.foundation.AvailableTimes;
import com.example.roombookingsystem.foundation.Room;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.awt.event.ActionEvent;
import java.util.Objects;

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

    public void setRoomDetails(AdHoc room){

        selectedRoomName.setText(room.getRoomName());
        selectedRoomCapacity.setText("Størrelse: " + room.getRoomSize() + " pladser");

        selectedRoomProjector.setText(room.isHasEquipment1() ?  "  Ja"  : "  Nej");
        selectedRoomWBboard.setText(room.isHasEquipment2() ?    "  Ja" : "  Nej");
        selectedRoomSpeakers.setText(room.isHasEquipment3() ?   "  Ja"  : "  Nej");
        selectedRoomPower.setText(room.isHasEquipment4() ?      "  Ja" : "  Nej");
    }


    @FXML
    public void btnBookClicked(javafx.event.ActionEvent actionEvent) {

    }
}
