package com.jdbc;

import com.jdbc.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class JDBCTest18 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "insert into t_batch(id,name) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            int count = 0;
            for (int i = 1; i <= 10000; i++) {
                preparedStatement.setLong(1, i);
                preparedStatement.setString(2, "Person" + i);
                //打包
                preparedStatement.addBatch();
                if (i % 500 == 0) {
                    //int[] ints = preparedStatement.executeBatch();
                    //System.out.println(Arrays.toString(ints));
                    //count += ints.length;
                    count += preparedStatement.executeBatch().length;
                }
            }
            //循环结束之后再次执行批处理，防止数据丢失
            count += preparedStatement.executeBatch().length;
            System.out.println("共插入了" + count + "条记录");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(connection, preparedStatement, null);
        }
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + "ms");
    }
}
