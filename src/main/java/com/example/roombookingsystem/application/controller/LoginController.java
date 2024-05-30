package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.application.FxmlView;
import com.example.roombookingsystem.application.SceneSwitcher;
import com.example.roombookingsystem.application.UserRoles;
import com.example.roombookingsystem.foundation.Login;
import com.example.roombookingsystem.foundation.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    Button btnLogin;
    @FXML
    TextField tfUsername;
    @FXML
    TextField tfPassword;

    @FXML
    public void initialize() {

    }

    public void btnLoginClick(MouseEvent mouseEvent) throws IOException, SQLException {
        Login.getInstance().login(tfUsername.getText(), tfPassword.getText());
        int loginId = Login.getInstance().getLoginID();
//        switch (Login.getInstance().getLoginID()) {
//            case UserRoles.ADMIN.getId():
//                break;
//            case UserRoles.GUEST.getId():
//                break;
//            default:
//                break;
//        }

        if (loginId == UserRoles.ADMIN.getId()) {
            System.out.println("YAY it worked!");
        }

        SceneSwitcher.getInstance().switchScene(FxmlView.HOME);
        SceneSwitcher.getInstance().getPrimaryStage().centerOnScreen();
    }
}
