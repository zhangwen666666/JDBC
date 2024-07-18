package com.jdbc;

import com.jdbc.utils.DbUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class JDBCTest21 {
    public static void main(String[] args) {
        Connection connection = null;
        CallableStatement cs = null;
        try {
            //注册驱动与获取连接
            connection = DbUtils.getConnection();

            //获取数据库操作对象
            String sql = "{? = call myfun(?,?)}";
            cs = connection.prepareCall(sql);
            //给第二个和第三个?传值
            cs.setInt(2,3);
            cs.setInt(3,4);
            //将第一个?设置为出参
            cs.registerOutParameter(1, Types.INTEGER);
            //执行SQL语句
            cs.execute();
            //获取结果
            int result = cs.getInt(1);
            System.out.println(result);//3! + 4! = 30
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DbUtils.close(connection,cs,null);
        }
    }
}
