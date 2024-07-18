package com.jdbc;

import com.jdbc.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest13 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "select ename from emp where ename like ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"_O%");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String ename = resultSet.getString("ename");
                System.out.println(ename);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(connection,preparedStatement,resultSet);
        }
    }
}
