package com.example.roombookingsystem.services;

import com.example.roombookingsystem.domain.Booking;
import com.example.roombookingsystem.persistence.CrudDAO.bookingDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CSVConverterTest {

    private CSVConverter csvConverter;
    private bookingDAOImpl mockBookingDAO;

    @BeforeEach
    public void setUp() throws Exception {
        csvConverter = new CSVConverter();
        mockBookingDAO = new bookingDAOImpl() {
            @Override
            public ArrayList<Booking> getBookingsByID(int userID) {
                ArrayList<Booking> bookings = new ArrayList<>();
                bookings.add(new Booking(1, "Meeting", Date.valueOf("2024-05-29"), Time.valueOf("10:00:00"), Time.valueOf("12:00:00"), true, 101, "Room1", userID));
                return bookings;
            }
        };

        // Inject mockBookingDAO into csvConverter using reflection
        java.lang.reflect.Field field = csvConverter.getClass().getDeclaredField("BDI");
        field.setAccessible(true);
        field.set(csvConverter, mockBookingDAO);
    }

    @Test
    public void testExportBookedHistoryToCSV() throws SQLException, IOException {
        System.out.println("Running testExportBookedHistoryToCSV");

        String filePath = "test_bookings.csv";
        csvConverter.exportBookedHistoryToCSV(filePath);

        File file = new File(filePath);
        assertTrue(file.exists());
        System.out.println("CSV file created: " + filePath);

        List<String> lines = Files.readAllLines(file.toPath());
        assertEquals(2, lines.size()); // Header + one booking
        System.out.println("Number of lines in CSV: " + lines.size());

        assertEquals("Title,Room,Date,TimeStart,TimeEnd", lines.get(0));
        assertEquals("Meeting,101,2024-05-29,10:00:00,12:00:00", lines.get(1));
        System.out.println("CSV content matches expected values");

        // Clean up the file after test
        file.delete();
        System.out.println("CSV file deleted after test");

        System.out.println("testExportBookedHistoryToCSV passed");
    }
}