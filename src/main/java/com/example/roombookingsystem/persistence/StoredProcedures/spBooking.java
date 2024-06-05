package com.example.roombookingsystem.persistence.StoredProcedures;

import com.example.roombookingsystem.foundation.AvailableTimes;
import com.example.roombookingsystem.foundation.Booking;
import com.example.roombookingsystem.foundation.databaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class spBooking {
    public ArrayList<AvailableTimes> getAvailableTimesFilter(Date date, Time timeStart, Time timeEnd, int minRoomSize, boolean requiresEq1, boolean requiresEq2, boolean requiresEq3, boolean requiresEq4, int roomID) {
        String query = "EXEC sp_GetAvailableRoomsFilter @Date = ?, @StartTime = ?, @EndTime = ?, @MinRoomSize = ?, @RequiresEquip1 = ?, @RequiresEquip2 = ?, @RequiresEquip3 = ?, @RequiresEquip4 = ?, @RoomID = ?";
        try {
            Connection connection = databaseConnection.getInstance();
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setDate(1, date);
            pstmt.setTime(2, timeStart);
            pstmt.setTime(3, timeEnd);
            pstmt.setInt(4, minRoomSize);
            pstmt.setBoolean(5, requiresEq1);
            pstmt.setBoolean(6, requiresEq2);
            pstmt.setBoolean(7, requiresEq3);
            pstmt.setBoolean(8, requiresEq4);
            pstmt.setInt(9, roomID);

            ResultSet resultSet = pstmt.executeQuery();

            ArrayList<AvailableTimes> array = new ArrayList<>();
            while (resultSet.next())
            {
                AvailableTimes availableTimes = new AvailableTimes(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getTime(4),
                        resultSet.getTime(5)
                );
                array.add(availableTimes);
            }
            return array;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
