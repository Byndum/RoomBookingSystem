package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.domain.Booking;
import com.example.roombookingsystem.domain.User;
import com.example.roombookingsystem.foundation.*;
import com.example.roombookingsystem.persistence.CrudDAO.BookingDAOImpl;
import com.example.roombookingsystem.persistence.GenericQuerries.DBUsers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

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
    @FXML
    ChoiceBox SelectTimeStart;
    @FXML
    ChoiceBox SelectTimeEnd;
    @FXML
    TextField nameID;
    @FXML
    TextField title;

    private Time timeEnd;
    private Time timeStart;

    public void initialize() {
        SelectTimeStart.valueProperty().addListener((obs,oldValue,newValue) -> {
            if(((Time)SelectTimeStart.getSelectionModel().getSelectedItem()).toLocalTime().isAfter(((Time) SelectTimeEnd.getSelectionModel().getSelectedItem()).toLocalTime())) {
                SelectTimeEnd.getSelectionModel().select(SelectTimeEnd.getItems().indexOf(SelectTimeStart.getSelectionModel().getSelectedItem())+1);
            }

        });
        SelectTimeEnd.valueProperty().addListener((obs,oldValue,newValue) -> {
            if(((Time)SelectTimeEnd.getSelectionModel().getSelectedItem()).toLocalTime().isBefore(((Time) SelectTimeStart.getSelectionModel().getSelectedItem()).toLocalTime())){
                SelectTimeStart.getSelectionModel().select(SelectTimeStart.getItems().indexOf(SelectTimeEnd.getSelectionModel().getSelectedItem())-1);

            }
        });


    }

    public void setTimes(Time timeStart, Time timeEnd) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        populateTimeStart();
        populateTimeEnd();
    }

    public void setRoomDetails(AdHoc room) {
        selectedRoomName.setText(room.getRoomName());
        selectedRoomCapacity.setText("Størrelse: " + room.getRoomSize() + " pladser");
        selectedRoomProjector.setText(room.isHasEquipment1() ? "  Ja" : "  Nej");
        selectedRoomWBboard.setText(room.isHasEquipment2() ? "  Ja" : "  Nej");
        selectedRoomSpeakers.setText(room.isHasEquipment3() ? "  Ja" : "  Nej");
        selectedRoomPower.setText(room.isHasEquipment4() ? "  Ja" : "  Nej");
        selectedRoomErrors.setText("Fejl: " + room.getFaults());
    }

    @FXML
    public void btnBookClicked(ActionEvent actionEvent) {
        BookingDAOImpl bookingDAO = new BookingDAOImpl();
        DBUsers dbUsers = new DBUsers();
        int chosenUserID = 2;
        for(User users : dbUsers.getAllUsers()){
            if(users.getUsername().equals(nameID.getText())){
                chosenUserID = users.getUserID();
            }
        }
        bookingDAO.addBooking(new Booking(
                0,
                title.getText(),
                Date.valueOf(LocalDate.now()),
                (Time) SelectTimeStart.getSelectionModel().getSelectedItem(),
                (Time) SelectTimeEnd.getSelectionModel().getSelectedItem(),
                false,
                AdHocController.selectedRoom.getRoomID(),
                null,
                chosenUserID
        ));
        AdHocController controller = (AdHocController) nameID.getScene().getWindow().getUserData();
        controller.refreshTableview();

        ((Stage) nameID.getScene().getWindow()).close();


    }
    @FXML
    public void btnCancelClick(ActionEvent actionEvent) {
        ((Stage) nameID.getScene().getWindow()).close();
    }

    private void populateTimeStart() {
        SelectTimeStart.getItems().clear();
        Time tempTime = timeStart;

        while (tempTime.toLocalTime().isBefore(timeEnd.toLocalTime())) {
            SelectTimeStart.getItems().add(tempTime);
            tempTime = Time.valueOf(tempTime.toLocalTime().plusMinutes(15));
        }
        SelectTimeStart.getSelectionModel().select(timeStart);
    }
    private void populateTimeEnd() {
        SelectTimeEnd.getItems().clear();
        Time tempTime = timeStart;

        while (tempTime.toLocalTime().isBefore(timeEnd.toLocalTime())) {
            tempTime = Time.valueOf(tempTime.toLocalTime().plusMinutes(15));
            SelectTimeEnd.getItems().add(tempTime);
        }
        SelectTimeEnd.getSelectionModel().select(timeEnd);
    }
}
