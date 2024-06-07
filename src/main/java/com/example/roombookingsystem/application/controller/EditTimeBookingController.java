package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.application.FxmlView;
import com.example.roombookingsystem.application.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Time;
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

    @FXML
    public void initialize() {}

    public void btnConfirmClick(MouseEvent mouseEvent) {
        //SceneSwitcher.getInstance().getPrimaryStage().setUserData(btnConfirm.getText());
        EmployeeBookingController controller = (EmployeeBookingController) SceneSwitcher.getInstance().getSecondaryStage().getUserData();
        controller.updateTable("Fuck YOU!");
        ((Stage) btnConfirm.getScene().getWindow()).close();
    }
}
