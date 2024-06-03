package com.example.roombookingsystem.foundation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private static Login instance;

    private int loginID;

    private Login() {}

    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }

    public int getLoginID() {
        return loginID;
    }

    public void login(String username, String password) throws SQLException {
        Connection con = databaseConnection.getInstance();
        PreparedStatement ps = con.prepareStatement("SELECT fldUserID FROM tblUser WHERE fldUsername=? AND fldPassword=?");
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            this.loginID = rs.getInt("fldUserID");
            System.out.println("Login ID: " + this.loginID);
        }
    }
}
