package com.example.roombookingsystem.application;

public enum UserRoles {
    GUEST(2),
    STUDENT(5),
    EMPLOYEE(3),
    MAINTENANCESTAFF(4),
    ADMIN(1);

    private int id;

    UserRoles(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
}
