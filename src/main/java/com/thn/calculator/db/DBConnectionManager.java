package com.thn.calculator.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
    private String URL = "jdbc:postgresql://localhost:5432/calculator";
    private String USER = "postgres";
    private String PASSWORD = "postgres";
    private Connection connection;

    public Connection getConnection(){
        try {
            return connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
