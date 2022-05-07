package com.thn.calculator.storage;

import com.thn.calculator.db.DBConnectionManager;
import com.thn.calculator.entity.Operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    private ResultSet prepareStory(long userId) {
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = new DBConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from operation where user_id=?");
            preparedStatement.setLong(1, userId);
            rs = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    @Override
    public List<Operation> getStory(long userId) {
        List<Operation> operations = new ArrayList<>();
        try {
            ResultSet rs = prepareStory(userId);
            while (rs.next()) {
                operations.add(new Operation(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getDouble(6)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return operations;
    }
}
