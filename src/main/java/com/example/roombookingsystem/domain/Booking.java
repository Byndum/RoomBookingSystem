package com.example.roombookingsystem.domain;

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

    public Time getTimeStart() {
        return timeStart;
    }
    public void setTimeStart(Time TimeStart) {
        this.timeStart =  TimeStart;
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


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    private int bookingID;
    private String title;
    private Date date;
    private Time timeStart;
    private Time timeEnd;
    private boolean catering;
    private int roomID;
    private String roomName;
    private int userID;
    private String day;

    public Booking (int bookingID, String title, Date date, Time timeStart, Time timeEnd, boolean catering, int roomID, String roomName, int userID)
    {
        this.bookingID = bookingID;
        this.title = title;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.catering = catering;
        this.roomID = roomID;
        this.roomName = roomName;
        this.userID = userID;

    }


    public Booking() {

    }
}