package com.jdbc.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * JDBC工具类
 */

public class DbUtils {
    private DbUtils(){}

    private static String driver;
    private static String url;
    private static String user;
    private static String password;


    static {
        //在类加载时注册驱动，对整个应用程序来说，注册驱动只需要做一次即可。
        //并且只执行一次
        ResourceBundle bundle = ResourceBundle.getBundle("com.jdbc.jdbc");
        driver = bundle.getString("driver");
        url = bundle.getString("url");
        user = bundle.getString("user");
        password = bundle.getString("password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取数据库连接对象
     * @return 连接对象
     * @throws SQLException 获取连接对象失败
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url,user,password);
        return connection;
    }

    /**
     *  关闭资源
     * @param conn 释放连接对象
     * @param statement 数据库操作对象
     * @param ret 结果集对象
     */
    public static void close(Connection conn, Statement statement, ResultSet ret){
        if (ret != null) {
            try {
                ret.close();
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
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
