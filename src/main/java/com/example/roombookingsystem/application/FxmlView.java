package com.example.roombookingsystem.application;

public enum FxmlView {
    LOGIN("/com/example/roombookingsystem/login-view.fxml"),
    HOME("/com/example/roombookingsystem/home-view.fxml"),
    EMPLOYEEBOOKING("/com/example/roombookingsystem/EmployeeBooking-view.fxml");

    private final String path;

    FxmlView(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
