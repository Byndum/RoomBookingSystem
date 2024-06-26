package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.application.FxmlView;
import com.example.roombookingsystem.application.SceneSwitcher;
import com.example.roombookingsystem.domain.Booking;
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
    private TableView<Booking> tableviewBookings;
    @FXML
    private TableColumn<Booking, String> tcRoomName;
    @FXML
    private TableColumn<Booking, String> tcTitle;
    @FXML
    private TableColumn<Booking, String> tcDate;
    @FXML
    private TableColumn<Booking, String> tcDay;
    @FXML
    private TableColumn<Booking, String> tcTimeStart;
    @FXML
    private TableColumn<Booking, String> tcTimeEnd;
    @FXML
    private TableColumn<Booking, String> tcErrors;
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

    @FXML
    public void initialize() {
        //TODO: Add indicator for what tab you're on
        //TODO: Maybe add an icon for the home page
        menuHome.setStyle("-fx-background-color: #00adef");
        menuBook.setStyle("-fx-background-color: #00adef");
        menuEdit.setStyle("-fx-background-color: #00adef");
        menuHistory.setStyle("-fx-background-color: #00adef");
        menuMyErrors.setStyle("-fx-background-color: #00adef");
        menuAllErrors.setStyle("-fx-background-color: #00adef");

        //region MouseEntered + MouseExited
        menuHome.setOnMouseEntered(mouseEvent -> {
            System.out.println("hover");
            menuHome.setStyle("-fx-background-color: #00bfff");
        });
        menuHome.setOnMouseExited(mouseEvent -> {
            System.out.println("no longer hover");
            menuHome.setStyle("-fx-background-color: #00adef");
            //menuHome.setStyle("-fx-background-color: #fcfcfc");
        });
        menuBook.setOnMouseEntered(mouseEvent -> {
            System.out.println("hover");
            menuBook.setStyle("-fx-background-color: #00bfff");
        });
        menuBook.setOnMouseExited(mouseEvent -> {
            System.out.println("no longer hover");
            menuBook.setStyle("-fx-background-color: #00adef");
            //menuHome.setStyle("-fx-background-color: #fcfcfc");
        });
        menuEdit.setOnMouseEntered(mouseEvent -> {
            System.out.println("hover");
            menuEdit.setStyle("-fx-background-color: #00bfff");
        });
        menuEdit.setOnMouseExited(mouseEvent -> {
            System.out.println("no longer hover");
            menuEdit.setStyle("-fx-background-color: #00adef");
            //menuHome.setStyle("-fx-background-color: #fcfcfc");
        });
        menuHistory.setOnMouseEntered(mouseEvent -> {
            System.out.println("hover");
            menuHistory.setStyle("-fx-background-color: #00bfff");
        });
        menuHistory.setOnMouseExited(mouseEvent -> {
            System.out.println("no longer hover");
            menuHistory.setStyle("-fx-background-color: #00adef");
            //menuHome.setStyle("-fx-background-color: #fcfcfc");
        });
        menuMyErrors.setOnMouseEntered(mouseEvent -> {
            System.out.println("hover");
            menuMyErrors.setStyle("-fx-background-color: #00bfff");
        });
        menuMyErrors.setOnMouseExited(mouseEvent -> {
            System.out.println("no longer hover");
            menuMyErrors.setStyle("-fx-background-color: #00adef");
            //menuHome.setStyle("-fx-background-color: #fcfcfc");
        });
        menuAllErrors.setOnMouseEntered(mouseEvent -> {
            System.out.println("hover");
            menuAllErrors.setStyle("-fx-background-color: #00bfff");
        });
        menuAllErrors.setOnMouseExited(mouseEvent -> {
            System.out.println("no longer hover");
            menuAllErrors.setStyle("-fx-background-color: #00adef");
            //menuHome.setStyle("-fx-background-color: #fcfcfc");
        });
        //endregion

        tcRoomName.setCellValueFactory(new PropertyValueFactory<Booking, String>("roomName"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<Booking, String>("title"));
        tcDate.setCellValueFactory(new PropertyValueFactory<Booking, String>("date"));
        tcDay.setCellValueFactory(new PropertyValueFactory<Booking, String>("day"));
        tcTimeStart.setCellValueFactory(new PropertyValueFactory<Booking, String>("timeStart"));
        tcTimeEnd.setCellValueFactory(new PropertyValueFactory<Booking, String>("timeEnd"));
        tcErrors.setCellValueFactory(new PropertyValueFactory<Booking, String>("errors"));
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
    }

    public void btnHistoryClick(MouseEvent mouseEvent) throws IOException {
        SceneSwitcher.getInstance().switchScene(FxmlView.MYBOOKINGSMAINTENANCESTAFF);
    }
}
