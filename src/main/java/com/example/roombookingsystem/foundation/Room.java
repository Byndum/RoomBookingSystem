package com.example.roombookingsystem.foundation;

public class Room {
    private int roomID;
    private String roomName;
    private int roomSize;

    public Room (int roomID, String roomName, int roomSize){
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomSize = roomSize;
    }

    public int getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }
    @Override
    public String toString() {
        return roomName;
    }
}
