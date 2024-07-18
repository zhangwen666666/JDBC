package com.jdbc;

import com.jdbc.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTest11 {
    public static void main(String[] args) {
        //使用PreparedStatement完成修改操作
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1.注册驱动
            //2.获取连接
            connection = DbUtils.getConnection();
            //3.获取预编译的数据库操作对象
            String sql = "update t_product set price = ? where name = ?";
            preparedStatement = connection.prepareStatement(sql);
            //给?赋值
            preparedStatement.setDouble(1, 30.0);
            preparedStatement.setString(2, "跳蛋");
            //4.执行SQL语句
            int count = preparedStatement.executeUpdate();
            System.out.println(count > 0 ? "修改成功" : "修改失败");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            DbUtils.close(connection, preparedStatement, null);
        }
    }
}
