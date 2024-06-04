package com.example.roombookingsystem.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EmployeeBookingController {

    @FXML
    Label EnkeltBookingLabel;
    @FXML
    Label BulkLabel;

    @FXML
    public void initialize() {
        toggleBoldText(EnkeltBookingLabel, BulkLabel);
        EnkeltBookingLabel.setOnMouseClicked(event -> toggleBoldText(EnkeltBookingLabel, BulkLabel));
        BulkLabel.setOnMouseClicked(event -> toggleBoldText(BulkLabel, EnkeltBookingLabel));
        //first parameter gets toggled to bold, second gets disabled.
    }

    private void toggleBoldText(Label labelToBold, Label labelToNormal) {
        Font boldFont = Font.font(labelToBold.getFont().getFamily(), FontWeight.BOLD, labelToBold.getFont().getSize());
        labelToBold.setFont(boldFont);

        Font normalFont = Font.font(labelToNormal.getFont().getFamily(), FontWeight.NORMAL, labelToNormal.getFont().getSize());
        labelToNormal.setFont(normalFont);
    }
}
