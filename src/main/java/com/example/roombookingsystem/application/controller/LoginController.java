package com.example.roombookingsystem.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    Button btnLogin;

    public void btnLoginClick(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/roombookingsystem/home-view.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
}
