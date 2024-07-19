package com.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTest24 {
    public static void main(String[] args) {
        DataSource dataSource = null;
        Connection connection = null;
        //获取一个输入流，指向一个属性资源文件
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
        //创建属性类对象
        Properties properties = new Properties();
        try {
            //将属性配置文件中的资源加载到属性类对象中
            properties.load(in);
            //获取连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            //通过连接池获取连接对象
            if (dataSource != null) {
                connection = dataSource.getConnection();
                //处理业务
                System.out.println(connection);//com.mysql.cj.jdbc.ConnectionImpl@456d6c1e
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
