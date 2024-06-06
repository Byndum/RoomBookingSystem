package com.example.roombookingsystem.persistence.GenericQuerries;

import com.example.roombookingsystem.foundation.Room;
import com.example.roombookingsystem.foundation.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBRooms {
    ArrayList<Room> rooms = new ArrayList<>();

    public ArrayList<Room> getAllRooms() {
        Connection connection = databaseConnection.getInstance();
        String query = "SELECT * FROM tblRoom";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                ));
            }
            return rooms;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
