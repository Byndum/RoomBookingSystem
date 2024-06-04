package com.example.roombookingsystem.foundation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class databaseConnection {
    // Instance
    private static final databaseConnection INSTANCE;

    static {
        try {
            INSTANCE = new databaseConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Connection
    private static final String dbName = "dbRoomBooking";
    private static final String username = "sa";
    private static final String password = "1234";
    private static final String port = "1433";
    private static final String url =   "jdbc:sqlserver://localhost:" + port +
                                        ";databaseName=" + dbName +
                                        ";encrypt=true;trustServerCertificate=true;";
    private Properties properties = new Properties();
    Connection connection;



    private databaseConnection() throws SQLException {
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        properties.setProperty("encrypt", "true");
        connection = DriverManager.getConnection(url, properties);
    }

    public static Connection getInstance() {
        return INSTANCE.connection;
    }
}
