package com.jdbc;

import com.jdbc.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTest17 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "insert into t_batch(id,name) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            int count = 0;
            for (int i = 1; i <= 10000; i++) {
                preparedStatement.setLong(1, i);
                preparedStatement.setString(2, "Person" + i);
                count += preparedStatement.executeUpdate();
            }
            System.out.println("共插入了" + count + "条记录");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(connection, preparedStatement, null);
        }
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end-start) + "ms");
    }
}
