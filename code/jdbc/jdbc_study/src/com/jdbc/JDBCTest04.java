package com.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class JDBCTest04 {
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
            String sql = "select id,name,price,create_time from t_product";
            rst = statement.executeQuery(sql);

            //5.处理结果集
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            while (rst.next()) {
                Long id = rst.getLong("id");
                String name = rst.getString("name");
                double price = rst.getDouble("price");
                java.sql.Date date = rst.getDate("create_time");
                java.util.Date javaDate = new java.util.Date(date.getTime());
                String formatDate = sdf.format(javaDate);
                System.out.println(id + "\t\t" + name + "\t\t" + price + "\t\t" + formatDate);
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

