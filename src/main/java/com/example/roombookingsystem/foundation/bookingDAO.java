package com.example.roombookingsystem.foundation;

import java.util.ArrayList;

public interface bookingDAO {
    void addBooking(Booking booking);
    ArrayList<Booking> getBookingsByID(int id);
    void updateBooking(Booking booking);
    void deleteBooking(int id);
}
