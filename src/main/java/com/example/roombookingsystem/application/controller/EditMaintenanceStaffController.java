package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.application.FxmlView;
import com.example.roombookingsystem.application.SceneSwitcher;
import com.example.roombookingsystem.foundation.ObservableBooking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class EditMaintenanceStaffController {
    //region FXML declarations
    @FXML
    private TableView<ObservableBooking> tableviewBookings;
    @FXML
    private TableColumn<ObservableBooking, String> tcRoomName;
    @FXML
    private TableColumn<ObservableBooking, String> tcTitle;
    @FXML
    private TableColumn<ObservableBooking, String> tcDate;
    @FXML
    private TableColumn<ObservableBooking, String> tcDay;
    @FXML
    private TableColumn<ObservableBooking, String> tcTimeStart;
    @FXML
    private TableColumn<ObservableBooking, String> tcTimeEnd;
    @FXML
    private TableColumn<ObservableBooking, String> tcErrors;
    @FXML
    private StackPane menuHome;
    @FXML
    private StackPane menuBook;
    @FXML
    private StackPane menuEdit;
    @FXML
    private StackPane menuHistory;
    @FXML
    private StackPane menuMyErrors;
    @FXML
    private StackPane menuAllErrors;
    //endregion

    //TODO: Move somewhere else where it's accessible and expandable
    ObservableList<ObservableBooking> data = FXCollections.observableArrayList(
            new ObservableBooking("402","Salgsmøde 1","2024-05-26","Monday", "12:15", "12:45", ""),
            new ObservableBooking("306","Salgsmøde 2","2024-05-26","Monday", "08:30", "10:00", ""),
            new ObservableBooking("420","Salgsmøde 3","2024-05-27","Tuesday", "11:45", "12:45", "")
    );

    @FXML
    public void initialize() {
        //TODO: Add indicator for what tab you're on
        //TODO: Add Text and maybe an icon for the home page
        menuHome.setStyle("-fx-background-color: #00adef");
        menuBook.setStyle("-fx-background-color: #00adef");
        menuEdit.setStyle("-fx-background-color: #00adef");
        menuHistory.setStyle("-fx-background-color: #00adef");
        menuMyErrors.setStyle("-fx-background-color: #00adef");
        menuAllErrors.setStyle("-fx-background-color: #00adef");

        tcRoomName.setCellValueFactory(new PropertyValueFactory<ObservableBooking, String>("roomName"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<ObservableBooking, String>("title"));
        tcDate.setCellValueFactory(new PropertyValueFactory<ObservableBooking, String>("date"));
        tcDay.setCellValueFactory(new PropertyValueFactory<ObservableBooking, String>("day"));
        tcTimeStart.setCellValueFactory(new PropertyValueFactory<ObservableBooking, String>("timeStart"));
        tcTimeEnd.setCellValueFactory(new PropertyValueFactory<ObservableBooking, String>("timeEnd"));
        tcErrors.setCellValueFactory(new PropertyValueFactory<ObservableBooking, String>("errors"));
    }

    public void menuHomeClick(MouseEvent mouseEvent) throws IOException {
        SceneSwitcher.getInstance().switchScene(FxmlView.HOMEMANINTENANCESTAFF);
    }

    public void btnBookClick(MouseEvent mouseEvent) throws IOException {
        SceneSwitcher.getInstance().createPopUp(FxmlView.EMPLOYEEBOOKING);
    }

    public void btnEditClick(MouseEvent mouseEvent) throws IOException {
        SceneSwitcher.getInstance().switchScene(FxmlView.EDITMAINTENANCESTAFF);
    }

    public void btnConfirmClick(ActionEvent actionEvent) {
        tableviewBookings.setItems(data);
    }
}
