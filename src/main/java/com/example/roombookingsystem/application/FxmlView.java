package com.example.roombookingsystem.application;

public enum FxmlView {
    LOGIN("/com/example/roombookingsystem/login-view.fxml",300,210),
    HOME("/com/example/roombookingsystem/home-view.fxml",1280,720),
    EMPLOYEEBOOKING("/com/example/roombookingsystem/EmployeeBooking-view.fxml",555,400);

    private final String path;
    private final int width;
    private final int height;

    FxmlView(String path, int width, int height) {
        this.path = path;
        this.width = width;
        this.height = height;
    }

    public String getPath() {
        return path;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
