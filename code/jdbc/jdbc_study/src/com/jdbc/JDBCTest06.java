package com.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest06 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rst = null;
        ResourceBundle bundle = ResourceBundle.getBundle("com.jdbc.jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        try {
            //1.注册驱动
            Class.forName(driver);

            //2.获取连接
            connection = DriverManager.getConnection(url, user, password);

            //3.创建数据库操纵对象
            statement = connection.createStatement();

            //4.执行sql语句
            String sql = "insert into t_product(name,price,create_time) values('小米su7',1.0,'2024-3-14')";
            int count = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            System.out.println("插入了" + count + "条记录");

            //获取新增行的住兼职
            rst = statement.getGeneratedKeys();
            //通过结果集取数据
            if(rst.next()){
                Long id = rst.getLong(1);
                System.out.println("新增行的主键值是" + id);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6.释放连接
            if (rst != null) {
                try {
                    rst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
