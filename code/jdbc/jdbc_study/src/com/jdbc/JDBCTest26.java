package com.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * 使用HikariCP连接池技术
 */
public class JDBCTest26 {
    public static void main(String[] args) throws Exception{
        //获取一个输入流，指向配置文件
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc02.properties");
        //创建一个属性类对象
        Properties properties = new Properties();
        //将属性配置文件中的资源加载到属性类对象中
        properties.load(in);
        //创建配置信息对象
        HikariConfig hikariConfig = new HikariConfig(properties);
        //获取连接池对象
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        //获取连接
        Connection connection = dataSource.getConnection();
        //....
        System.out.println(connection);
        //关闭连接(将连接池中该对象的属性修改为空闲，并不会真的释放连接)
        connection.close();
    }
}
