package com.example.roombookingsystem.foundation;

public class ObservableBooking {
   private String roomName;
   private String title;
   private String date;
   private String day;
   private String timeStart;
   private String timeEnd;
   private String errors;

   public ObservableBooking(String roomName, String title, String date, String day, String timeStart, String timeEnd, String errors) {
       this.roomName = roomName;
       this.title = title;
       this.date = date;
       this.day = day;
       this.timeStart = timeStart;
       this.timeEnd = timeEnd;
       this.errors = errors;
   }

    public String getRoomName() {
        return roomName;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getErrors() {
        return errors;
    }
}
