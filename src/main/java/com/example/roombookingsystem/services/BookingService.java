package com.example.roombookingsystem.services;
import com.example.roombookingsystem.foundation.Booking;
import com.example.roombookingsystem.foundation.bookingDAO;
import com.example.roombookingsystem.domain.TimeSlot;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private bookingDAO bookingDAO;

    public BookingService(bookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public List<TimeSlot> calculateAvailableTimeSlots(LocalDate date, int roomID, LocalTime timeStart, LocalTime endTime) throws SQLException{
        List<Booking> bookings = bookingDAO.getBookingsForDateAndRoom(date, roomID);
        List<TimeSlot> availableSlots = new ArrayList<>();

        //Add initial time slot covering the whole range
        List<TimeSlot> allSlots = new ArrayList<>();
        allSlots.add(new TimeSlot(timeStart, endTime));

        //Remove slots that overlap existing.
        for (Booking booking : bookings) {
            LocalTime bookingStart = booking.getTimeStart().toLocalTime();
            LocalTime bookingEnd = booking.getTimeEnd().toLocalTime();
            allSlots = splitTimeSlots(allSlots, bookingStart, bookingEnd);
        }
        //Filter out slots smaller than 15 minutes)
        for (TimeSlot slot: allSlots) {
            if (slot.getTimeEnd().minusMinutes(15).isAfter(slot.getTimeStart())) {
                availableSlots.add(slot);
            };
        }
        return availableSlots;
    }
    
    private List<TimeSlot> splitTimeSlots(List<TimeSlot> slots, LocalTime start, LocalTime end) {
        List<TimeSlot> result = new ArrayList<>();
        for (TimeSlot slot: slots) {
            if (slot.getTimeStart().isBefore(start) && slot.getTimeEnd().isAfter(end)) {
                result.add(new TimeSlot(slot.getTimeStart(), start));
                result.add(new TimeSlot(slot.getTimeEnd(), end));
            }
            else if (slot.getTimeStart().isBefore(start) && slot.getTimeEnd().isAfter(start))
            {
                result.add(new TimeSlot(slot.getTimeStart(), start));
            }
            else if (slot.getTimeStart().isBefore(end) && slot.getTimeEnd().isAfter(end))
            {
                result.add(new TimeSlot(slot.getTimeEnd(), end));
            }
            else if (slot.getTimeEnd().isBefore(start) || slot.getTimeStart().isAfter(end))
            {
                result.add(slot);
            }
        }
        return result;
    }
}
