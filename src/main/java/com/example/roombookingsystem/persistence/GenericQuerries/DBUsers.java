package com.example.roombookingsystem.persistence.GenericQuerries;

import com.example.roombookingsystem.foundation.User;
import com.example.roombookingsystem.foundation.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBUsers {
    private ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getAllUsers() {
        Connection con = databaseConnection.getInstance();
        String query = "SELECT * FROM tblUser";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)
                ));
            }
            return users;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
