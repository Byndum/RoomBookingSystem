package com.example.roombookingsystem.persistence.CrudDAO;

import com.example.roombookingsystem.foundation.Booking;
import com.example.roombookingsystem.foundation.bookingDAO;
import com.example.roombookingsystem.foundation.databaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.util.Locale;
import java.sql.Date;

public class bookingDAOImpl implements bookingDAO {

    private static ArrayList<Booking> todaysBookings = new ArrayList<>();

    public void addBooking(Booking booking) {
        String query = "INSERT INTO tblBooking (fldTitle, fldDate, fldTimeStart, fldTimeEnd, fldCatering, fldRoomID, fldUserID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = databaseConnection.getInstance();
            PreparedStatement pstmt = connection.prepareStatement(query);

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

    @Override
    public void deleteBooking(int id) {
        String query = "DELETE FROM tblBooking WHERE fldBookId = ?";
        try {
            Connection connection = databaseConnection.getInstance();
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ArrayList<Booking> getBookingsByID(int id) {
        ArrayList<Booking> array = new ArrayList<>();
        String query = "SELECT * FROM tblBooking WHERE fldUserID = ?";
        try {
            Connection connection = databaseConnection.getInstance();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next())
            {
                Booking booking = new Booking(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getTime(4),
                        resultSet.getTime(5),
                        resultSet.getBoolean(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8));
                array.add(booking);
            }
            return array;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTodaysBookingsArrayList() {
        String query = "SELECT * FROM tblBooking";
        try {
            Connection connection = databaseConnection.getInstance();
            PreparedStatement pstmt = connection.prepareStatement(query);

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
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Search query for looking at specific dates and rooms. Can be changed if needed.
    public List<Booking> getBookingsForDateAndRoom(LocalDate date, int roomId){
    String query = "SELECT * FROM tblBooking WHERE fldDate = ? AND fldRoomID = ?";
    try {
        Connection connection = databaseConnection.getInstance();
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.setDate(1, Date.valueOf(date));
        pstmt.setInt(2, roomId);
        ResultSet rs = pstmt.executeQuery();

        List<Booking> bookings = new ArrayList<>();
        while(rs.next()){
                Booking booking = new Booking();
                booking.setTitle(rs.getString("fldTitle"));
                booking.setDate(rs.getDate("fldDate"));
                booking.setTimeStart(rs.getTime("fldTimeStart"));
                booking.setTimeEnd(rs.getTime("fldTimeEnd"));
                bookings.add(booking);
            }
        {
            return bookings;
        }

        } catch (SQLException e) {
        throw new RuntimeException(e);
        }
    }


    @Override
    public void updateBooking(Booking booking) {
        String query = "UPDATE tblBooking SET " +
                "fldRoomID = (SELECT fldRoomID FROM tblRoom WHERE fldRoomName = ?), " +
                "fldDate = ?, " +
                "fldTimeStart = ?, " +
                "fldTimeEnd = ?, " +
                "fldCatering = ?, " +
                "fldUserID = ?" +
                " WHERE fldBookId = ?";

        try {
            Connection connection = databaseConnection.getInstance();
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setInt(1, booking.getRoomID());
            pstmt.setDate(2, booking.getDate());
            pstmt.setTime(3, booking.getTimeStart());
            pstmt.setTime(4, booking.getTimeEnd());
            pstmt.setBoolean(5, booking.isCatering());
            pstmt.setInt(6, booking.getUserID());
            pstmt.setInt(7, booking.getBookingID());

            pstmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}