package com.jdbc;

import com.jdbc.utils.DbUtils;

import java.sql.*;
import java.util.Scanner;

/**
 * 使用PreparedStatement解决SQL注入问题
 */
public class JDBCTest09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //1.初始化登录界面
        System.out.println("欢迎使用用户管理系统，请登录：");
        System.out.print("用户名：");
        String loginName = scanner.nextLine();
        System.out.print("密码：");
        String loginPassword = scanner.nextLine();

//        System.out.println("用户名：" + loginName);
//        System.out.println("密码：" + loginPassword);

        //2. 连接数据库，验证用户名和密码是否正确
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet ret = null;

        try {
            //3. 获取连接
            connection = DbUtils.getConnection();

            //4. 获取预编译的数据库操纵对象
            String sql = "select * from t_user where name = ? and password = ?";
            statement = connection.prepareStatement(sql);

            //给 ? 传值
            statement.setString(1,loginName);
            statement.setString(2,loginPassword);

            //5. 执行SQL语句
            System.out.println(sql);
            ret = statement.executeQuery();
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
