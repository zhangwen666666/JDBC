package com.jdbc;

import com.jdbc.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCTest10 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1.注册驱动
            //2.获取连接
            connection = DbUtils.getConnection();
            //3.获取预编译数据库操纵对象
            String sql = "insert into emp(empno,ename,job,mgr,hiredate,sal,comm,deptno) values(?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            //给?传值
            preparedStatement.setInt(1, 8888);
            preparedStatement.setString(2, "张三");
            preparedStatement.setString(3, "销售员");
            preparedStatement.setInt(4, 7369);
            LocalDate date = LocalDate.parse("2024-01-01");
            preparedStatement.setDate(5, java.sql.Date.valueOf(date));
            preparedStatement.setDouble(6, 1000.0);
            preparedStatement.setDouble(7, 500.0);
            preparedStatement.setInt(8, 10);

            //4.执行SQL语句
            int count = preparedStatement.executeUpdate();
            System.out.println(count > 0 ? "插入了" + count + "条记录" : "插入失败");
            //5.处理查询结果集
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            DbUtils.close(connection, preparedStatement, null);
        }
    }
}

