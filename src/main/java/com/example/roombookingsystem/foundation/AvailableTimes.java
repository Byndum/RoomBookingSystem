package com.example.roombookingsystem.foundation;

import java.sql.Time;

public class AvailableTimes {
    private int roomID;
    private String roomName;
    private Time timeStart;
    private Time timeEnd;
    private int roomSize;
    private String faults;
    private String actionsText;

    public AvailableTimes(int roomID, String roomName, int roomSize, Time timeStart, Time timeEnd) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomSize = roomSize;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.faults = "!";
    }

    public int getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public Time getTimeStart() {
        return timeStart;
    }

    public Time getTimeEnd() {
        return timeEnd;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public String getFaults() {
        return faults;
    }
    public Time setTimeStart(Time timeStart) {
        return this.timeStart = timeStart;
    }
    public Time setTimeEnd(Time timeEnd) {
        return this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return roomName + " " + timeStart + " - " + timeEnd;
    }
}
