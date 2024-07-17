package com.jdbc;

import java.sql.*;
import java.util.Scanner;

public class JDBCInsert {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        Statement statement = null;
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            String url = "jdbc:mysql://127.0.0.1:3306/jdbc?user=root&password=1234";
            connection = DriverManager.getConnection(url);

            statement = connection.createStatement();
            System.out.print("请输入你要插入的记录条数：");
            int num = scanner.nextInt();
            while (num > 0) {
                num--;
                System.out.print("请输入商品名称：");
                String name = scanner.next();
                System.out.print("请输入商品价格：");
                double price = scanner.nextDouble();
                System.out.print("请输入商品生产日期：");
                String createTime = scanner.next();
                String sql = "insert into t_product(name,price,create_time) values('" + name + "'," + price + ",'" + createTime + "')";
//                System.out.println(sql);
                int count = statement.executeUpdate(sql);
                System.out.println(count > 0 ? "插入成功" : "插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
