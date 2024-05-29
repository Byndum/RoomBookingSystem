package com.example.roombookingsystem.foundation;

public interface DBDAO {
    void addBooking(Booking booking);
    Booking getBookingByID(int id);
    void updateBooking(Booking booking);
    void deleteBooking(int id);
}
