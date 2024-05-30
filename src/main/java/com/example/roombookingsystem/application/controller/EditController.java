package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.application.FxmlView;
import com.example.roombookingsystem.application.SceneSwitcher;
import com.example.roombookingsystem.foundation.ObservableBooking;
import com.example.roombookingsystem.foundation.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class EditController {
    @FXML
    private TableView<ObservableBooking> tableviewBookings = new TableView();
    @FXML
    private TableColumn<ObservableBooking, String> tcRoomName = new TableColumn<>("tcRoom");
    @FXML
    private TableColumn<ObservableBooking, String> tcTitle = new TableColumn<>("tcTitle");
    @FXML
    private TableColumn<ObservableBooking, String> tcDate = new TableColumn<>("tcDate");
    @FXML
    private TableColumn<ObservableBooking, String> tcDay = new TableColumn<>("tcDay");
    @FXML
    private TableColumn<ObservableBooking, String> tcTimeStart = new TableColumn<>("Start Time");
    @FXML
    private TableColumn<ObservableBooking, String> tcTimeEnd = new TableColumn<>("End Time");
    @FXML
    private TableColumn<ObservableBooking, String> tcErrors = new TableColumn<>("Room Errors");
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

    ObservableList<ObservableBooking> data = FXCollections.observableArrayList(
            new ObservableBooking("402","Salgsmøde 1","2024-05-26","Monday", "12:15", "12:45", ""),
            new ObservableBooking("306","Salgsmøde 2","2024-05-26","Monday", "08:30", "10:00", ""),
            new ObservableBooking("420","Salgsmøde 3","2024-05-27","Tuesday", "11:45", "12:45", "")
    );

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

        tcRoomName.setCellValueFactory(new PropertyValueFactory<ObservableBooking, String>("roomName"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<ObservableBooking, String>("title"));
        tcDate.setCellValueFactory(new PropertyValueFactory<ObservableBooking, String>("date"));
        tcDay.setCellValueFactory(new PropertyValueFactory<ObservableBooking, String>("day"));
        tcTimeStart.setCellValueFactory(new PropertyValueFactory<ObservableBooking, String>("timeStart"));
        tcTimeEnd.setCellValueFactory(new PropertyValueFactory<ObservableBooking, String>("timeEnd"));
        tcErrors.setCellValueFactory(new PropertyValueFactory<ObservableBooking, String>("errors"));
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
        tableviewBookings.setItems(data);
        System.out.println(data);
    }
}
