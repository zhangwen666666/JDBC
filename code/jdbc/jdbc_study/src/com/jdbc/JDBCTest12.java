package com.jdbc;

import com.jdbc.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTest12 {
    public static void main(String[] args) {
        String sql = "delete from emp where empno=?";
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, 8888);
            int count = preparedStatement.executeUpdate();
            System.out.println(count > 0 ? "删除" + count + "条记录" : "删除失败");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
