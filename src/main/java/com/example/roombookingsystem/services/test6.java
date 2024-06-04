package com.example.roombookingsystem.services;

import com.example.roombookingsystem.foundation.Booking;
import com.example.roombookingsystem.foundation.bookingDAO;
import com.example.roombookingsystem.persistence.CrudDAO.bookingDAOImpl;
import com.example.roombookingsystem.services.CSVConverter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test6 {
    public static void main(String[] args) {
        try {

            bookingDAO bookingDAO = new bookingDAOImpl();
            CSVConverter csvConverter = new CSVConverter(bookingDAO);

            String timestamp = new SimpleDateFormat("dd.MM.yyyy_HH;mm;ss").format(new Date());

            // Export bookings to CSV
            csvConverter.exportBookedHistoryToCSV("C:\\Java\\RoomBookingSystem\\src\\main\\resources\\com\\example\\roombookingsystem\\CSVFiles\\Statistic Date; "+timestamp);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}