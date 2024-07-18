package com.jdbc;

import com.jdbc.utils.DbUtils;

import java.io.*;
import java.sql.*;

public class JDBCTest16 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        InputStream binaryStream = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "select img from t_img where name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"狗狗的图片");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                binaryStream = resultSet.getBinaryStream("img");
                OutputStream os = new FileOutputStream("E:dir/dog.jpg");
                byte[] bytes = new byte[1024];
                int count = 0;
                while((count = binaryStream.read(bytes,0,1024))!=-1){
                    os.write(bytes,0,count);
                }
                os.flush();
                binaryStream.close();
                os.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(connection,preparedStatement,resultSet);
        }
    }
}
