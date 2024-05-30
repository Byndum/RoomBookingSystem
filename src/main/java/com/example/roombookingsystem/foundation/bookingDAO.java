package com.example.roombookingsystem.foundation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface bookingDAO {
    void addBooking(Booking booking);
    Booking getBookingByID(int id);
    void updateBooking(Booking booking);
    void deleteBooking(int id);
    List<Booking> getBookingsForDateAndRoom(LocalDate date, int roomId) throws SQLException;

}
