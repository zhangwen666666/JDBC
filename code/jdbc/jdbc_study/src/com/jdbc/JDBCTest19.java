package com.jdbc;

import com.jdbc.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTest19 {
    public static void main(String[] args) {
        double money = 10000.0;
        Connection connection = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
            connection = DbUtils.getConnection();

            //开启事务
            connection.setAutoCommit(false);

            String sql1 = "update t_act set balance = balance - ? where actno = ?";
            ps1 = connection.prepareStatement(sql1);
            ps1.setDouble(1, money);
            ps1.setString(2, "act-001");
            int count = ps1.executeUpdate();
            System.out.println(count > 0 ? "转账成功" : "转账失败");

            //模拟异常
            String s = null;
            s.toString();

            String sql2 = "update t_act set balance = balance + ? where actno = ?";
            ps2 = connection.prepareStatement(sql2);
            ps2.setDouble(1, money);
            ps2.setString(2, "act-002");
            int count1 = ps2.executeUpdate();
            System.out.println(count1 > 0 ? "成功到账" : "未收到转账");

            //提交事务
            connection.commit();

        } catch (Exception e) {
            //有任何异常发生，回滚
            try {
                connection.rollback();
                System.out.println("捕捉异常");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        } finally {
            DbUtils.close(null, ps1, null);
            DbUtils.close(null, ps2, null);
            DbUtils.close(connection, null, null);
        }
    }
}
