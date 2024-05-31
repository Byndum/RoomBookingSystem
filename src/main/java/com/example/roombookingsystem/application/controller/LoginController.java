package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.application.FxmlView;
import com.example.roombookingsystem.application.SceneSwitcher;
import com.example.roombookingsystem.application.UserRoles;
import com.example.roombookingsystem.foundation.Login;
import com.example.roombookingsystem.foundation.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
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
    PasswordField pfPassword;
    @FXML
    public void initialize() {

    }

    public void btnLoginClick(MouseEvent mouseEvent) throws IOException, SQLException {
        Login.getInstance().login(tfUsername.getText(), pfPassword.getText());
        System.out.println(pfPassword.getText());
        int loginId = Login.getInstance().getLoginID();
        switch (UserRoles.getRole(loginId)) {
            case ADMIN:
                break;
            case EMPLOYEE:
                break;
            case MAINTENANCESTAFF:
                break;
            case STUDENT:
                break;
        }

//        if (loginId == UserRoles.ADMIN.getId()) {
//            System.out.println("YAY it worked!");
//        }

        SceneSwitcher.getInstance().switchScene(FxmlView.HOME);
        SceneSwitcher.getInstance().getPrimaryStage().centerOnScreen();
    }
}
