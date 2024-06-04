package com.example.roombookingsystem.domain;

import java.time.LocalTime;

public class TimeSlot {
    private LocalTime timeStart;
    private LocalTime timeEnd;

    public TimeSlot (LocalTime start, LocalTime end){
        this.timeStart = start;
        this.timeEnd = end;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString(){
        return "TimeSlot{" +
                "start=" + timeStart +
                ", end=" + timeEnd +
                '}';
    }
}
