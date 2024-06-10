package com.example.roombookingsystem.application.controller;

import com.example.roombookingsystem.application.FxmlView;
import com.example.roombookingsystem.application.SceneSwitcher;
import com.example.roombookingsystem.domain.Booking;
import com.example.roombookingsystem.domain.Login;
import com.example.roombookingsystem.domain.User;
import com.example.roombookingsystem.persistence.CrudDAO.BookingDAOImpl;
import com.example.roombookingsystem.persistence.GenericQuerries.DBUsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;

public class BookingHistoryMaintenanceStaffController {
    DBUsers dbUsers = new DBUsers();
    ArrayList<User> users = dbUsers.getAllUsers();
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
    private StackPane menuAllErrors;
    @FXML
    private StackPane menuBook;
    @FXML
    private StackPane menuEdit;
    @FXML
    private StackPane menuHistory;
    @FXML
    private StackPane menuHome;
    @FXML
    private StackPane menuMyErrors;
    //endregion
    @FXML
    void btnBookClick(MouseEvent event) throws IOException {
        SceneSwitcher.getInstance().createPopUp(FxmlView.EMPLOYEEBOOKING);
    }
    @FXML
    void btnEditClick(MouseEvent event) throws IOException {
        SceneSwitcher.getInstance().switchScene(FxmlView.EDITADMIN);
    }
    @FXML
    void btnHistoryClick(MouseEvent event) throws IOException {
        SceneSwitcher.getInstance().switchScene(FxmlView.MYBOOKINGSADMIN);
    }
    @FXML
    void menuHomeClick(MouseEvent event) throws IOException {
        SceneSwitcher.getInstance().switchScene(FxmlView.HOMEADMIN);
    }
    @FXML
    public void initialize() {
        //region Set initial colors for the side-menubar
        menuHome.setStyle("-fx-background-color: #00adef");
        menuBook.setStyle("-fx-background-color: #00adef");
        menuEdit.setStyle("-fx-background-color: #00adef");
        menuHistory.setStyle("-fx-background-color: #00adef");
        menuMyErrors.setStyle("-fx-background-color: #00adef");
        menuAllErrors.setStyle("-fx-background-color: #00adef");
        //endregion
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

        for (User user : users) {
            if (user.getUsername().equals(Login.getInstance().getLoginUsername())) {
                Login.getInstance().setLoginUserObj(user);
            }
        }

        populateTableview(Login.getInstance().getLoginUserObj());
    }
    public void populateTableview(User user) {
        BookingDAOImpl bDao = new BookingDAOImpl();
        ObservableList<Booking> list = FXCollections.observableArrayList(bDao.getBookingsByID(user.getUserID()));
        tableviewBookings.setItems(list);
    }
}
