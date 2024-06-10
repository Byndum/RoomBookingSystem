package com.example.roombookingsystem.application;

public enum FxmlView {
    LOGIN("/com/example/roombookingsystem/login-view.fxml",300,210),
    HOMEADMIN("/com/example/roombookingsystem/homeAdmin-view.fxml",1280,720),
    HOMEMANINTENANCESTAFF("/com/example/roombookingsystem/homeMaintenanceStaff-view.fxml",1280,720),
    HOMEEMPLOYEE("/com/example/roombookingsystem/homeEmployee-view.fxml",1280,720),
    EMPLOYEEBOOKING("/com/example/roombookingsystem/employeeBooking-view.fxml",555,400),
    EDITADMIN("/com/example/roombookingsystem/editAdmin-view.fxml",1280,720),
    EDITEMPLOYEE("/com/example/roombookingsystem/editEmployee-view.fxml",1280,720),
    EDITMAINTENANCESTAFF("/com/example/roombookingsystem/editMaintenanceStaff-view.fxml",1280,720),
    MYBOOKINGSADMIN("/com/example/roombookingsystem/myBookingsAdmin-view.fxml",1280,720),
    MYBOOKINGSEMPLOYEE("/com/example/roombookingsystem/myBookingsEmployee-view.fxml",1280,720),
    MYBOOKINGSMAINTENANCESTAFF("/com/example/roombookingsystem/myBookingsMaintenanceStaff-view.fxml",1280,720),
    ADHOC("/com/example/roombookingsystem/ad-Hoc-view.fxml",600,400),
    ROOMDETAILS("/com/example/roombookingsystem/roomDetails-view.fxml",354,458),
    EDITTIMEBOOKING("/com/example/roombookingsystem/editTimeBooking-view.fxml",270,82);
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
