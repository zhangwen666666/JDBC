package com.jdbc;

import com.jdbc.utils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 使用JDBC实现员工信息管理
 */
public class JDBCTest22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menu();
        while (true) {
//            menu();
            System.out.print("\n请输入功能编号：");
            int option = scanner.nextInt();
            switch (option) {
                case 0 -> {
                    System.out.println("退出系统，欢迎下次使用");
                    return;
                }
                case 1 -> {
                    //查看员工列表
                    showList();
                }
                case 2 -> {
                    //查看员工详细信息
                    showList();
                    System.out.print("请输入员工编号：");
                    long id = scanner.nextLong();
                    String detail = getDetail(id);
                    System.out.println(detail == null ? "不存在编号为" + id + "的员工" : detail);
                }
                case 3 -> {
                    System.out.print("请输入员工姓名：");
                    String name = scanner.next();
                    System.out.print("请输入员工岗位：");
                    String job = scanner.next();
                    System.out.print("请输入员工月薪：");
                    double salary = scanner.nextDouble();
                    System.out.print("请输入员工入职日期：");
                    String hiredate = scanner.next();
                    System.out.print("请输入员工住址：");
                    String address = scanner.next();
                    long id = save(name, job, salary, hiredate, address);
                    System.out.println(id > 0 ? "新增成功,新增的员工编号为：" + id : "新增失败");
                }
                case 4 -> {
                    showList();
                    System.out.print("请输入你要修改的员工编号：");
                    long id = scanner.nextLong();
                    String detail = getDetail(id);
                    if (detail == null) {
                        System.out.println("你输入的员工编号" + id + "不存在");
                        break;
                    }
                    System.out.println(detail);
                    System.out.print("请输入员工姓名：");
                    String name = scanner.next();
                    System.out.print("请输入员工岗位：");
                    String job = scanner.next();
                    System.out.print("请输入员工月薪：");
                    double salary = scanner.nextDouble();
                    System.out.print("请输入员工入职日期：");
                    String hiredate = scanner.next();
                    System.out.print("请输入员工住址：");
                    String address = scanner.next();
                    boolean ret = modify(id, name, job, salary, hiredate, address);
                    System.out.println(ret ? "员工信息更新成功" : "员工信息更新失败");
                }
                case 5 -> {
                    showList();
                    System.out.print("请输入你要删除的员工编号：");
                    long id = scanner.nextLong();
                    String detail = getDetail(id);
                    if (detail == null) {
                        System.out.println("你输入的员工编号" + id + "不存在");
                        break;
                    }
                    boolean ret = delete(id);
                    System.out.println(ret?"删除成功":"删除失败");
                    showList();
                }
                default -> {
                    System.out.println("你的选择无效，请重新选择");
                }
            }
        }
    }

    private static void menu() {
        System.out.println("\n欢迎使用员工信息管理,请认真阅读使用说明：");
        System.out.println("请输入对应的功能编号选择功能：");
        System.out.println("[1] 查看员工列表");
        System.out.println("[2] 查看员工详细信息");
        System.out.println("[3] 新增员工");
        System.out.println("[4] 修改员工");
        System.out.println("[5] 删除员工");
        System.out.println("[0] 退出系统");
    }

    private static boolean delete(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "delete from t_employee where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            int count = preparedStatement.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(connection, preparedStatement, null);
        }
        return false;
    }

    private static boolean modify(long id, String name, String job, double salary, String hiredate, String address) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "update t_employee set name = ?,job = ?,salary = ?,hiredate = ?,address = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, job);
            preparedStatement.setDouble(3, salary);
            preparedStatement.setString(4, hiredate);
            preparedStatement.setString(5, address);
            preparedStatement.setLong(6, id);
            int count = preparedStatement.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(connection, preparedStatement, null);
        }
        return false;
    }

    private static long save(String name, String job, double salary, String hiredate, String address) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "insert into t_employee(name,job,salary,hiredate,address) values(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, job);
            preparedStatement.setDouble(3, salary);
            preparedStatement.setString(4, hiredate);
            preparedStatement.setString(5, address);
            preparedStatement.executeUpdate();
            long id = -1;
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(connection, preparedStatement, resultSet);
        }
        return -1;
    }

    private static List<String> getList() {
        List<String> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "select id,name,job from t_employee";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String job = resultSet.getString("job");
                list.add("id=" + id + "\tname=" + name + "\tjob=" + job);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.close(connection, preparedStatement, resultSet);
        }
        return list;
    }

    public static String getDetail(long no) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "select id,name,job,hiredate,salary,address from t_employee where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, no);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String job = resultSet.getString("job");
                String hiredate = resultSet.getString("hiredate");
                double salary = resultSet.getDouble("salary");
                String address = resultSet.getString("address");
                return "id=" + id + "\tname=" + name + "\tjob=" + job + "\thiredate=" + hiredate + "\tsalary=" + salary + "\taddress=" + address;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.close(connection, preparedStatement, resultSet);
        }
    }

    public static void showList() {
        List<String> list = getList();
        if (list.isEmpty()) {
            System.out.println("无任何员工");
            return;
        }
        System.out.println("员工列表如下：");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
