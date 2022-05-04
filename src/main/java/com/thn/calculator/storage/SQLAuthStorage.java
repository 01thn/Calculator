package com.thn.calculator.storage;

import com.thn.calculator.db.DBConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLAuthStorage implements AuthStorage {
    @Override
    public boolean isAuthenticated(String login, String password) {
        boolean result = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = new DBConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from users where login=? and password=?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) result = true;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public Long getId(String login) {
        Long id = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = new DBConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from users where login=?");
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}

