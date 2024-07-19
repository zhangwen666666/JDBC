package com.jdbc.dao;

import com.jdbc.beans.Employee;
import com.jdbc.utils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 完成员工表t_employee中数据的增删改查
 * 增删改查也被简称为CRUD。
 * C：create 增
 * R：Read 读
 * U:
 */
public class EmployeeDao extends BaseDao{
    /**
     * 新增员工
     * @param employee 员工数据
     * @return 1表示新增了1条记录，返回其他表示新增失败
     */
    public int insert(Employee employee){
/*
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            connection = DbUtils.getConnection();
            String sql = "insert into t_employee(name,job,salary,hiredate,address) values(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,employee.getName());
            preparedStatement.setString(2,employee.getJob());
            preparedStatement.setDouble(3,employee.getSalary());
            preparedStatement.setString(4,employee.getHiredate());
            preparedStatement.setString(5,employee.getAddress());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(connection,preparedStatement,null);
        }
        return count;
*/

        String sql = "insert into t_employee(name,job,salary,hiredate,address) values(?,?,?,?,?)";
        return executeUpdate(sql,employee.getName(),employee.getJob(),employee.getSalary(),employee.getHiredate(),employee.getAddress());
    }

    /**
     * 根据id删除员工信息
     * @param id 员工id
     * @return 1表示删除成功，其他值表示删除失败
     */
    public int deleteById(Long id){
        /*Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            connection = DbUtils.getConnection();
            String sql = "delete from t_employee where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(connection,preparedStatement,null);
        }
        return count;*/
        String sql = "delete from t_employee where id = ?";
        return executeUpdate(sql,id);
    }

    /**
     * 修改员工信息
     * @param employee 新的员工信息（新的员工信息和旧的员工信息id是不变的）
     * @return 1表示修改成功，其他值表示修改失败
     */
    public int update(Employee employee){
        /*Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            connection = DbUtils.getConnection();
            String sql = "update t_employee set name = ?,job = ?,salary = ?,hiredate = ?,address = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,employee.getName());
            preparedStatement.setString(2,employee.getJob());
            preparedStatement.setDouble(3,employee.getSalary());
            preparedStatement.setString(4, employee.getHiredate());
            preparedStatement.setString(5,employee.getAddress());
            preparedStatement.setLong(6,employee.getId());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(connection,preparedStatement,null);
        }
        return count;*/

        String sql = "update t_employee set name = ?,job = ?,salary = ?,hiredate = ?,address = ? where id = ?";
        return executeUpdate(sql,employee.getName(),employee.getJob(),employee.getSalary(),employee.getHiredate(),employee.getAddress(),employee.getId());
    }

    /**
     * 根据id获取员工信息
     * @param id 员工id
     * @return 员工信息
     */
    public Employee selectById(Long id){
        /*Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Employee employee = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "select id,name,job,salary,hiredate,address from t_employee where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                employee = new Employee(id,
                        resultSet.getString("name"),
                        resultSet.getString("job"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("hiredate"),
                        resultSet.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(connection,preparedStatement,resultSet);
        }
        return employee;*/
        String sql = "select id,name,job,salary,hiredate,address from t_employee where id = ?";
        return executeQueryOne(Employee.class, sql, id);
    }

    /**
     * 获取所有员工信息
     * @return 员工列表
     */
    public List<Employee> selectAll(){
        /*Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Employee> employeeList = new ArrayList<>();
        try {
            connection = DbUtils.getConnection();
            String sql = "select id,name,job,salary,hiredate,address from t_employee";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Employee employee = new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("job"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("hiredate"),
                        resultSet.getString("address"));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(connection,preparedStatement,resultSet);
        }
        return employeeList;*/
        String sql = "select id,name,job,salary,hiredate,address from t_employee";
        return executeQuery(Employee.class,sql);
    }
}
