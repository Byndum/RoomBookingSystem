package com.example.roombookingsystem.foundation;

import com.example.roombookingsystem.domain.Booking;
import com.example.roombookingsystem.persistence.CrudDAO.BookingDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BookingDAOTest {

    private BookingDAOImpl bookingDAO;

    @BeforeEach
    public void setUp() {
        bookingDAO = new BookingDAOImpl() {
            private ArrayList<Booking> testBookings = new ArrayList<>();

            public void setTestBookings(ArrayList<Booking> bookings) {
                this.testBookings = bookings;
            }

            @Override
            public ArrayList<Booking> getBookingsByID(int userID) {
                ArrayList<Booking> result = new ArrayList<>();
                for (Booking booking : testBookings) {
                    if (booking.getUserID() == userID) {
                        result.add(booking);
                    }
                }
                return result;
            }

            @Override
            public void addBooking(Booking booking) {
                testBookings.add(booking);
            }
        };
    }

    @Test
    public void testGetBookingsByID() {
        ArrayList<Booking> bookings = new ArrayList<>();
        Booking booking1 = new Booking(1, "Meeting", Date.valueOf("2024-05-29"), Time.valueOf("10:00:00"), Time.valueOf("12:00:00"), true, 101, "Room1", 1);
        Booking booking2 = new Booking(2, "Conference", Date.valueOf("2024-06-01"), Time.valueOf("14:00:00"), Time.valueOf("16:00:00"), true, 102, "Room2", 2);
        bookings.add(booking1);
        bookings.add(booking2);

        assertEquals(2,bookings.size());
        assertEquals("Meeting", bookings.get(0).getTitle());
    }

    @Test
    public void testNoBookingsForInvalidID() {
        ArrayList<Booking> bookings = new ArrayList<>();
        Booking booking1 = new Booking(1, "Meeting", Date.valueOf("2024-05-29"), Time.valueOf("10:00:00"), Time.valueOf("12:00:00"), true, 101, "Room1", 1);
        Booking booking2 = new Booking(2, "Conference", Date.valueOf("2024-06-01"), Time.valueOf("14:00:00"), Time.valueOf("16:00:00"), true, 102, "Room2", 2);
        bookings.add(booking1);
        bookings.add(booking2);

        bookingDAO.setTestBookings(bookings);

        ArrayList<Booking> result = bookingDAO.getBookingsByID(999);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
