package com.jdbc;

import com.jdbc.utils.DbUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCTest08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //1.初始化登录界面
        System.out.println("欢迎使用用户管理系统，请登录：");
        System.out.print("用户名：");
        String loginName = scanner.next();
        System.out.print("密码：");
        String loginPassword = scanner.next();

//        System.out.println("用户名：" + loginName);
//        System.out.println("密码：" + loginPassword);

        //2. 连接数据库，验证用户名和密码是否正确
        Connection connection = null;
        Statement statement = null;
        ResultSet ret = null;

        try {
            //3. 获取连接
            connection = DbUtils.getConnection();
            //4. 创建数据库操纵对象
            statement = connection.createStatement();
            //5. 执行SQL语句
            String sql = "select * from t_user where name = '" + loginName +
                    "' and password = '" + loginPassword + "'";
            System.out.println(sql);
            ret = statement.executeQuery(sql);
            //6. 处理结果集
            if (ret.next()) {
                String name = ret.getString("realname");
                System.out.println("欢迎用户" + name + "登陆！！");
            } else {
                System.out.println("登录失败！！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(connection, statement, ret);
        }
    }
}
