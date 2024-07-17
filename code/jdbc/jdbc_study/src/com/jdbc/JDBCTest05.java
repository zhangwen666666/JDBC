package com.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest05 {
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
            String sql = "select * from t_product";
            rst = statement.executeQuery(sql);

            //5.处理结果集
            ResultSetMetaData metaData = rst.getMetaData();
            int count = metaData.getColumnCount();
            System.out.println("此表共有" + count + "列");
            for (int i = 1; i <= count; i++) {
                String columnName = metaData.getColumnName(i);
                String columnTypeName = metaData.getColumnTypeName(i);
                int length = metaData.getColumnDisplaySize(i);
                System.out.println("第" + i + "列的字段名：" + columnName + ",数据类型：" + columnTypeName + ",长度：" + length);
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
