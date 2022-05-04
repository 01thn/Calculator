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
}

