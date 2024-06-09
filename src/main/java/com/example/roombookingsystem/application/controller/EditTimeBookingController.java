package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.application.FxmlView;
import com.example.roombookingsystem.application.SceneSwitcher;
import com.example.roombookingsystem.foundation.AvailableTimes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

public class EditTimeBookingController {
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnConfirm;
    @FXML
    private ChoiceBox<Time> timeEnd;
    @FXML
    private ChoiceBox<Time> timeStart;
    private EmployeeBookingController controller = (EmployeeBookingController) SceneSwitcher.getInstance().getPreviouseLoadedStage().getUserData();

    @FXML
    public void initialize() {
        Time tempStartTime = ((AvailableTimes) controller.getListViewAvailableTimes()
                .getSelectionModel()
                .getSelectedItem()).getTimeStart();
        Time tempEndTime = ((AvailableTimes) controller.getListViewAvailableTimes()
                .getSelectionModel()
                .getSelectedItem()).getTimeEnd();
        //newTime.toLocalTime().plusMinutes(15);
        timeStart.getItems().add(tempStartTime);
        timeEnd.getItems().add(tempEndTime);
        while (tempStartTime.toLocalTime().isBefore(tempEndTime.toLocalTime())) {
            timeStart.getItems().add(Time.valueOf(tempStartTime.toLocalTime().plusMinutes(15)));
            timeEnd.getItems().add(Time.valueOf(tempStartTime.toLocalTime().plusMinutes(15)));
        }
    }

    public void btnConfirmClick(MouseEvent mouseEvent) {
        controller.updateTable(Time.valueOf(timeStart.getSelectionModel().getSelectedItem().toLocalTime()), Time.valueOf(timeEnd.getSelectionModel().getSelectedItem().toLocalTime()));
        ((Stage) btnConfirm.getScene().getWindow()).close();
    }
}
