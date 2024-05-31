package com.example.roombookingsystem.application;

public enum FxmlView {
    LOGIN("/com/example/roombookingsystem/login-view.fxml",300,210),
    HOMEADMIN("/com/example/roombookingsystem/homeAdmin-view.fxml",1280,720),
    HOMEMANINTENANCESTAFF("/com/example/roombookingsystem/homeMaintenanceStaff-view.fxml",1280,720),
    HOMEEMPLOYEE("/com/example/roombookingsystem/homeEmployee-view.fxml",1280,720),
    EMPLOYEEBOOKING("/com/example/roombookingsystem/employeeBooking-view.fxml",555,400),
    EDIT("/com/example/roombookingsystem/edit-view.fxml",1280,720),
    ADHOC("/com/example/roombookingsystem/adHoc-view.fxml",600,400);


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
