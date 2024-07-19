package com.jdbc;

import com.jdbc.utils.DbUtils02;

import java.sql.Connection;

public class JDBCTest25 {
    public static void main(String[] args) throws Exception{
        Connection connection01 = DbUtils02.getConnection();
        Connection connection02 = DbUtils02.getConnection();
        Connection connection03 = DbUtils02.getConnection();
        Connection connection04 = DbUtils02.getConnection();
        Connection connection05 = DbUtils02.getConnection();

        System.out.println(connection01);
        System.out.println(connection02);
        System.out.println(connection03);
        System.out.println(connection04);
        System.out.println(connection05);
    }
}
