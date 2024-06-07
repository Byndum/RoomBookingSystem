package com.example.roombookingsystem.foundation;

import java.sql.Time;
import java.util.ArrayList;

public class AdHoc {
    public void setFaults(String faults) {
        this.faults = faults;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    public void setTimeEnd(Time timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    private int roomID;
    private String roomName;
    private Time timeStart;
    private Time timeEnd;
    private int roomSize;
    private boolean hasEquipment1;
    private boolean hasEquipment2;

    public boolean isHasEquipment1() {
        return hasEquipment1;
    }

    public void setHasEquipment1(boolean hasEquipment1) {
        this.hasEquipment1 = hasEquipment1;
    }

    public boolean isHasEquipment2() {
        return hasEquipment2;
    }

    public void setHasEquipment2(boolean hasEquipment2) {
        this.hasEquipment2 = hasEquipment2;
    }

    public boolean isHasEquipment3() {
        return hasEquipment3;
    }

    public void setHasEquipment3(boolean hasEquipment3) {
        this.hasEquipment3 = hasEquipment3;
    }

    public boolean isHasEquipment4() {
        return hasEquipment4;
    }

    public void setHasEquipment4(boolean hasEquipment4) {
        this.hasEquipment4 = hasEquipment4;
    }

    private boolean hasEquipment3;
    private boolean hasEquipment4;
    private String faults;


    private AdHoc(int roomID, String roomName, Time timeStart, Time timeEnd, int roomSize) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.roomSize = roomSize;
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

    public static ArrayList<AdHoc> getRoomArray(ArrayList<AvailableTimes> atArray, ArrayList<Room> rArray) {
        ArrayList<AdHoc> AdHocRooms = new ArrayList<>();

        for (AvailableTimes a : atArray)
        {
            AdHocRooms.add(new AdHoc(a.getRoomID(), a.getRoomName(), a.getTimeStart(), a.getTimeEnd(), a.getRoomSize()));
        }

        for (AdHoc a : AdHocRooms)
        {
            for (Room r : rArray)
            {
                if (a.getRoomID() == r.getRoomID())
                {
                    a.setFaults(r.getFaults());
                    a.setHasEquipment1(r.isHasEquipment1());
                    a.setHasEquipment2(r.isHasEquipment2());
                    a.setHasEquipment3(r.isHasEquipment3());
                    a.setHasEquipment4(r.isHasEquipment4());
                }
            }
        }
        return AdHocRooms;
    }
}
