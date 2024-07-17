package com.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest03 {
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
            String sql = "select realname,id,name,password from t_user";
            rst = statement.executeQuery(sql);

            //5.处理结果集
            while (rst.next()) {
                String realname = rst.getString(1);
                String id = rst.getString(2);
                String name = rst.getString(3);
                String pwd = rst.getString(4);
                System.out.println(realname + "\t" + id + "\t" + name + "\t" + pwd);
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
