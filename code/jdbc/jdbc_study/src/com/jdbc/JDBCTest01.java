package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCTest01 {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("com.jdbc.jdbc");
            String driver = bundle.getString("driver");
            Class.forName(driver);

            String url = bundle.getString("url");
            String user = bundle.getString("user");
            String password = bundle.getString("password");
            connection = DriverManager.getConnection(url,user,password);
            System.out.println(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
