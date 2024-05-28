package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.application.FxmlView;
import com.example.roombookingsystem.application.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LoginController {
    @FXML
    Button btnLogin;

    public void btnLoginClick(MouseEvent mouseEvent) throws IOException {
        SceneSwitcher.getInstance().switchScene(FxmlView.HOME);
        SceneSwitcher.getInstance().getPrimaryStage().centerOnScreen();
    }
}
