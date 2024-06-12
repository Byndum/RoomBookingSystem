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
        Time initialStart = ((AvailableTimes) controller.getListViewAvailableTimes()
                .getSelectionModel()
                .getSelectedItem()).getTimeStart();
        Time initialEnd = ((AvailableTimes) controller.getListViewAvailableTimes()
                .getSelectionModel()
                .getSelectedItem()).getTimeEnd();
        Time tempStartTime = initialStart;
        Time tempEndTime = initialEnd;

        do {
            timeStart.getItems().add(Time.valueOf(tempStartTime.toLocalTime()));
            timeEnd.getItems().add(Time.valueOf(tempStartTime.toLocalTime()));

            tempStartTime = Time.valueOf(tempStartTime.toLocalTime().plusMinutes(15));
        } while (tempStartTime.toLocalTime().isBefore(tempEndTime.toLocalTime()));
        timeEnd.getItems().add(Time.valueOf(tempEndTime.toLocalTime()));

        timeStart.getSelectionModel().select(initialStart);
        timeEnd.getSelectionModel().select(initialEnd);
    }

    public void btnConfirmClick(MouseEvent mouseEvent) {
        controller.updateTable(Time.valueOf(timeStart.getSelectionModel().getSelectedItem().toLocalTime()), Time.valueOf(timeEnd.getSelectionModel().getSelectedItem().toLocalTime()));
        ((Stage) btnConfirm.getScene().getWindow()).close();
    }
}
