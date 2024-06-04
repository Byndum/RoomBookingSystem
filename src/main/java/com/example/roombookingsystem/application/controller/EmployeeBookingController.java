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
    }

    private void toggleBoldText(Label labelToBold, Label labelToNormal) {
        Font boldFont = Font.font(labelToBold.getFont().getFamily(), FontWeight.BOLD, labelToBold.getFont().getSize());
        labelToBold.setFont(boldFont);

        Font normalFont = Font.font(labelToNormal.getFont().getFamily(), FontWeight.NORMAL, labelToNormal.getFont().getSize());
        labelToNormal.setFont(normalFont);
    }
}
