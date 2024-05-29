package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.application.FxmlView;
import com.example.roombookingsystem.application.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class EditController {
    @FXML
    private TableView tableViewBookings = new TableView();
    @FXML
    private StackPane menuHome;
    @FXML
    private StackPane menuBook;
    @FXML
    private StackPane menuEdit;
    @FXML
    private StackPane menuHistory;
    @FXML
    private StackPane menuRegisterRoom;
    @FXML
    private StackPane menuRegisterError;
    @FXML
    private StackPane menuAllErrors;

    @FXML
    public void initialize() {
        menuHome.setStyle("-fx-background-color: #00adef");
        //menuHome.setStyle("-fx-background-color: linear-gradient(to right, #00adef, #f4f4f4)");
        menuBook.setStyle("-fx-background-color: #00adef");
        menuEdit.setStyle("-fx-background-color: #00adef");
        menuHistory.setStyle("-fx-background-color: #00adef");
        menuRegisterRoom.setStyle("-fx-background-color: #00adef");
        menuRegisterError.setStyle("-fx-background-color: #00adef");
        menuAllErrors.setStyle("-fx-background-color: #00adef");
        //System.out.println(tableViewBookings.getColumns().get(1));

    }

    public void menuHomeClick(MouseEvent mouseEvent) throws IOException {
        SceneSwitcher.getInstance().switchScene(FxmlView.HOME);
    }

    public void btnBookClick(MouseEvent mouseEvent) throws IOException {
        SceneSwitcher.getInstance().createPopUp(FxmlView.EMPLOYEEBOOKING);
    }

    public void btnEditClick(MouseEvent mouseEvent) throws IOException {
        SceneSwitcher.getInstance().switchScene(FxmlView.EDIT);
    }

    public void btnConfirmClick(ActionEvent actionEvent) {
    }
}
