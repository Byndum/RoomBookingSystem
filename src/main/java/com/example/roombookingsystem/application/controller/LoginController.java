package com.example.roombookingsystem.application.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class LoginController {

    public void btnLogin(MouseEvent mouseEvent) {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/roombookingsystem/home-view.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        System.out.println("Login");
    }
}
