package com.example.roombookingsystem.foundation;

public class User {
    private int userID;
    private String username;
    private String email;
    private String phoneNumber;
    private int userRoleID;

    public User (int userID, String username, String email, String phoneNumber, int userRoleID)
    {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userRoleID = userRoleID;
    }
    public String getUsername() {
        return username;
    }
    public int getUserID() {
        return userID;
    }
    public int getUserRoleID() {
        return userRoleID;
    }
    @Override
    public String toString() {
        return username;
    }
}
