package com.jdbc.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 使用Druid连接池对DbUtils工具类进行改造
 */
public class DbUtils02 {
    private DbUtils02() {
    }

    private static DataSource dataSource = null;

    static {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取数据库连接对象
     *
     * @return 连接对象
     * @throws SQLException 获取连接对象失败
     */
    public static Connection getConnection() throws SQLException {
        if (dataSource == null)
            return null;
        return dataSource.getConnection();
    }

    /**
     * 关闭资源
     *
     * @param conn      释放连接对象
     * @param statement 数据库操作对象
     * @param ret       结果集对象
     */
    public static void close(Connection conn, Statement statement, ResultSet ret) {
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
