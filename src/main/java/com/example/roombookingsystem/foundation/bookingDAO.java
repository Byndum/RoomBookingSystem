package com.example.roombookingsystem.foundation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import java.util.ArrayList;

public interface bookingDAO {
    void addBooking(Booking booking);
    ArrayList<Booking> getBookingsByID(int userID);
    ArrayList<Booking> getAllBookings();
    void updateBooking(Booking booking);
    void deleteBooking(int id);
    List<Booking> getBookingsForDateAndRoom(LocalDate date, int roomId) throws SQLException;

}
