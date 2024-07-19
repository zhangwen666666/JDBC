package com.jdbc;

import com.jdbc.beans.Employee;
import com.jdbc.dao.EmployeeDao;
import com.jdbc.utils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 使用DAO改造后的员工信息管理
 */
public class JDBCTest23 {
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
                case 1 ->
                    //查看员工列表
                        showList();
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
                    int count = save(name, job, salary, hiredate, address);
                    System.out.println(count > 0 ? "新增成功" : "新增失败");
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
                    System.out.println(ret ? "删除成功" : "删除失败");
                    showList();
                }
                default -> System.out.println("你的选择无效，请重新选择");
            }
        }
    }

    private static EmployeeDao employeeDao = new EmployeeDao();

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
        int flag = employeeDao.deleteById(id);
        return flag == 1;
    }

    private static boolean modify(long id, String name, String job, double salary, String hiredate, String address) {
        Employee employee = new Employee(id, name, job, salary, hiredate, address);
        int ret = employeeDao.update(employee);
        return ret == 1;
    }

    private static int save(String name, String job, double salary, String hiredate, String address) {
        Employee employee = new Employee(name, job, salary, hiredate, address);
        return employeeDao.insert(employee);
    }

    private static List<String> getList() {
        List<Employee> employeeList = employeeDao.selectAll();
        List<String> list = new ArrayList<>();
        for (Employee employee : employeeList) {
            list.add("id = " + employee.getId() + "\tname = " + employee.getName() + "\tjob = " + employee.getJob());
        }
        return list;
    }

    public static String getDetail(long no) {
        Employee employee = employeeDao.selectById(no);
        return employee == null ? null : employee.toString();
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
