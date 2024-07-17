package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCTest02 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
//        String url = "jdbc:mysql://127.0.0.1:3306/jdbc?user=root&password=1234";
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
        Properties properties = new Properties();
        properties.put("user","root");
        properties.put("password","1234");
//        Connection connection = DriverManager.getConnection(url);
        Connection connection = DriverManager.getConnection(url,properties);
        System.out.println(connection);
    }
}
