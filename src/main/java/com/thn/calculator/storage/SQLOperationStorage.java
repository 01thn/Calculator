package com.thn.calculator.storage;

import com.thn.calculator.db.DBConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLOperationStorage implements OperationStorage {
    @Override
    public void save(long userId, double var1, double var2, String operation, double result) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = new DBConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into operation (user_id, var_1, var_2, operation, result) " +
                            "values (?,?,?,?,?)");
            preparedStatement.setLong(1, userId);
            preparedStatement.setDouble(2, var1);
            preparedStatement.setDouble(3, var2);
            preparedStatement.setString(4, operation);
            preparedStatement.setDouble(5, result);
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
