package com.example.roombookingsystem.foundation;

import java.sql.Date;
import java.sql.Time;

public class Booking {
    public int getBookingID() {
        return bookingID;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
    public Time getTimeEnd() {
        return timeEnd;
    }

    public boolean isCatering() {
        return catering;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setTimeEnd(Time timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setCatering(boolean catering) {
        this.catering = catering;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    private int bookingID;
    private String title;
    private Date date;
    private Time time;
    private Time timeEnd;
    private boolean catering;
    private int roomID;
    private int userID;

    public Booking (int bookingID, String title, Date date, Time time, Time timeEnd, boolean catering, int roomID, int userID)
    {
        this.bookingID = bookingID;
        this.title = title;
        this.date = date;
        this.time = time;
        this.timeEnd = timeEnd;
        this.catering = catering;
        this.roomID = roomID;
        this.userID = userID;
    }
}
