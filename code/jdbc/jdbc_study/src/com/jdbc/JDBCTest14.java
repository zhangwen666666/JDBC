package com.jdbc;

import com.jdbc.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest14 {
    public static void main(String[] args) {
        int pageSize = 3;
        int pageNum = 5;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "select ename from emp limit ?,?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (pageNum - 1) * pageSize);
            preparedStatement.setInt(2, pageSize);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String ename = resultSet.getString("ename");
                System.out.println(ename);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(connection, preparedStatement, resultSet);
        }
    }
}
