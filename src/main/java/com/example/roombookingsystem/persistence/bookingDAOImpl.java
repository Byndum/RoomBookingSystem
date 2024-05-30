package com.example.roombookingsystem.persistence;

import com.example.roombookingsystem.foundation.Booking;
import com.example.roombookingsystem.foundation.bookingDAO;
import com.example.roombookingsystem.foundation.databaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class bookingDAOImpl implements bookingDAO {

    private static ArrayList<Booking> todaysBookings = new ArrayList<>();

    public void addBooking(Booking booking) {
        String query = "INSERT INTO tblBooking (fldTitle, fldDate, fldTimeStart, fldTimeEnd, fldCatering, fldRoomID, fldUserID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getInstance();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, booking.getTitle());
            pstmt.setDate(2, booking.getDate());
            pstmt.setTime(3, booking.getTime());
            pstmt.setTime(4, booking.getTimeEnd());
            pstmt.setBoolean(5, booking.isCatering());
            pstmt.setInt(6, booking.getRoomID());
            pstmt.setInt(7, booking.getUserID());

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Booking getBookingByID(int id) {
        return null;
    }

    @Override
    public void deleteBooking(int id) {

    }

    public void updateTodaysBookingsArrayList() {
        String query = "SELECT * FROM tblBooking";
        try (Connection connection = databaseConnection.getInstance();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next())
            {
                todaysBookings.add(new Booking(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getTime(4),
                        resultSet.getTime(5),
                        resultSet.getBoolean(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8)
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Booking> getBookingArray()
    {
        return todaysBookings;
    }


    @Override
    public void updateBooking(Booking booking)
    {
        String query = "UPDATE tblBooking SET " +
                "fldRoomID = (SELECT fldRoomID FROM tblRoom WHERE fldRoomName = " + booking.getRoomID() + "), " +
                "fldDate = " + booking.getDate() + ", " +
                "fldTimeStart = " + booking.getTime() + ", " +
                "fldTimeEnd = " + booking.getTimeEnd() + ", " +
                "fldCatering = " + booking.isCatering() + " " +
                "WHERE fldBookId = " + booking.getBookingID();

        System.out.println(query);
    }
}
