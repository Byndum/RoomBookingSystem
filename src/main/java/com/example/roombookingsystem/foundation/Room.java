package com.example.roombookingsystem.foundation;

public class Room {
    private int roomID;
    private String roomName;
    private int roomSize;
    private boolean hasEquipment1;
    private boolean hasEquipment2;
    private boolean hasEquipment3;
    private boolean hasEquipment4;


    public void setFaults(String faults) {
        this.faults = faults;
    }

    private String faults;

    public Room (int roomID, String roomName, int roomSize, boolean hasEquipment1, boolean hasEquipment2, boolean hasEquipment3, boolean hasEquipment4, String faults){
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomSize = roomSize;
        this.hasEquipment1 = hasEquipment1;
        this.hasEquipment2 = hasEquipment2;
        this.hasEquipment3 = hasEquipment3;
        this.hasEquipment4 = hasEquipment4;
        this.faults = faults;
    }

    public int getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public boolean isHasEquipment1() {
        return hasEquipment1;
    }

    public boolean isHasEquipment2() {
        return hasEquipment2;
    }

    public boolean isHasEquipment3() {
        return hasEquipment3;
    }

    public boolean isHasEquipment4() {
        return hasEquipment4;
    }

    public String getFaults() {
        return faults;
    }

    @Override
    public String toString() {
        return roomName;
    }
}