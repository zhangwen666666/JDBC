package com.jdbc;

import com.jdbc.utils.DbUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class JDBCTest20 {
    public static void main(String[] args) {
        Connection connection = null;
        CallableStatement cs = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "{call mypro(?,?)}";
            cs = connection.prepareCall(sql);//对以上的SQL语句预编译
            cs.setInt(1,100);
            //将第二个占位符注册为出参
            cs.registerOutParameter(2, Types.INTEGER);
            //调用存储过程
            cs.execute();
            //获取执行结果
            int ret = cs.getInt(2);
            System.out.println(ret);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DbUtils.close(connection,cs,null);
        }
    }
}
