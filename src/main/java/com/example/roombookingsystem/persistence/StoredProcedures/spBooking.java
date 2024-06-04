package com.example.roombookingsystem.persistence.StoredProcedures;

import com.example.roombookingsystem.foundation.Booking;
import com.example.roombookingsystem.foundation.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class spBooking {
    public void addBooking(Booking booking) {
        String query = "INSERT INTO tblBooking (fldTitle, fldDate, fldTimeStart, fldTimeEnd, fldCatering, fldRoomID, fldUserID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getInstance();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, booking.getTitle());
            pstmt.setDate(2, booking.getDate());
            pstmt.setTime(3, booking.getTimeStart());
            pstmt.setTime(4, booking.getTimeEnd());
            pstmt.setBoolean(5, booking.isCatering());
            pstmt.setInt(6, booking.getRoomID());
            pstmt.setInt(7, booking.getUserID());

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
