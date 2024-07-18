package com.jdbc;

import com.jdbc.utils.DbUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTest15 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        FileInputStream in = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "insert into t_img(name,img) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "狗狗的图片");
            in = new FileInputStream("jdbc_study/src/com/jdbc/dog.jpg");
            preparedStatement.setBlob(2, in);
            int count = preparedStatement.executeUpdate();
            System.out.println("插入了" + count + "条记录");
//            String currentDirectory = System.getProperty("user.dir");
//            System.out.println(currentDirectory);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            DbUtils.close(connection, preparedStatement, null);
        }
    }
}
