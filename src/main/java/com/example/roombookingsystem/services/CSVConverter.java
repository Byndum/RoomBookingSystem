package com.example.roombookingsystem.services;

import com.example.roombookingsystem.domain.Booking;
import com.example.roombookingsystem.persistence.CrudDAO.bookingDAOImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CSVConverter {

    private final bookingDAOImpl BDI = new bookingDAOImpl();

    public CSVConverter (){
    }

    public void exportBookedHistoryToCSV (String filePath) throws SQLException, IOException {
        List<Booking> bookings = BDI.getBookingsByID(2);


        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Title,Room,Date,TimeStart,TimeEnd\n");
            for (Booking booking : bookings){
                writer.append(String.valueOf(booking.getTitle())).append(',');
                writer.append(String.valueOf(booking.getRoomID())).append(',');
                writer.append(String.valueOf(booking.getDate())).append(',');
                writer.append(String.valueOf(booking.getTimeStart())).append(',');
                writer.append(String.valueOf(booking.getTimeEnd())).append(','+"\n");
            }
            writer.close();
        }
    }
}
