package com.example.roombookingsystem.domain;

import com.example.roombookingsystem.foundation.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private static Login instance;

    private User LoginUserObj;
    private int loginID;
    private String loginUsername;

    private Login() {}

    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }

    public User getLoginUserObj() {
        return LoginUserObj;
    }
    public int getLoginID() {
        return loginID;
    }
    public String getLoginUsername() {
        return loginUsername;
    }
    public void setLoginUserObj(User userObj) {
        this.LoginUserObj = userObj;
    }

    public void login(String username, String password) throws SQLException {
        Connection con = DBConnection.getInstance();
        PreparedStatement ps = con.prepareStatement("SELECT fldUserID FROM tblUser WHERE fldUsername=? AND fldPassword=?");
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            this.loginID = rs.getInt("fldUserID");
            this.loginUsername = username;
            System.out.println("Login ID: " + this.loginID);


        }
    }
}
