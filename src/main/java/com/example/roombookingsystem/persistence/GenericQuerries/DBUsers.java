package com.example.roombookingsystem.persistence.GenericQuerries;

import com.example.roombookingsystem.foundation.User;
import com.example.roombookingsystem.foundation.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class DBUsers {
    private ArrayList<User> users;

    public ArrayList<User> getAllUsers() {
        Connection con = databaseConnection.getInstance();
        String query = "SELECT * FROM users";

        try {
            PreparedStatement ps = con.prepareStatement(query);

        } catch (Exception e) {

        }

        return users;
    }
}
