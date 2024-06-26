package com.example.roombookingsystem.persistence.GenericQuerries;

import com.example.roombookingsystem.domain.Room;
import com.example.roombookingsystem.foundation.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBRooms {
    ArrayList<Room> rooms = new ArrayList<>();

    public ArrayList<Room> getAllRooms() {
        Connection connection = DBConnection.getInstance();
        String query = "SELECT \n" +
                "    r.fldRoomID,\n" +
                "    r.fldRoomName,\n" +
                "    r.fldRoomSize,\n" +
                "    MAX(CASE WHEN re.fldEquipmentID = 1 THEN 'True' ELSE 'False' END) AS Equipment1,\n" +
                "    MAX(CASE WHEN re.fldEquipmentID = 2 THEN 'True' ELSE 'False' END) AS Equipment2,\n" +
                "    MAX(CASE WHEN re.fldEquipmentID = 3 THEN 'True' ELSE 'False' END) AS Equipment3,\n" +
                "    MAX(CASE WHEN re.fldEquipmentID = 4 THEN 'True' ELSE 'False' END) AS Equipment4\n" +
                "FROM \n" +
                "    tblRoom r\n" +
                "LEFT JOIN \n" +
                "    tblRoomEquipment re ON r.fldRoomID = re.fldRoomID\n" +
                "GROUP BY \n" +
                "    r.fldRoomID, r.fldRoomName, r.fldRoomSize";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getBoolean(4),
                        rs.getBoolean(5),
                        rs.getBoolean(6),
                        rs.getBoolean(7),
                        ""
                ));
            }
            rs.close();
            ps.close();

            for (Room r : rooms) {
                query = "SELECT fldFaultTxt FROM tblFault WHERE fldRoomID = ? AND fldFaultStatusID < 3";
                ps = connection.prepareStatement(query);
                ps.clearParameters();

                ps.setInt(1, r.getRoomID());

                rs = ps.executeQuery();

                while (rs.next()) {
                    r.setFaults(r.getFaults() + ", " + rs.getString(1));
                }
                rs.close();
                ps.close();
            }

            return rooms;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}