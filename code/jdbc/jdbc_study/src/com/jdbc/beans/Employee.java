package com.jdbc.beans;

/**
 * 员工类，专门做数据疯转的。封装了员工信息
 * 这个类被称为bean,或者pojo类,也就是：普通的Java类
 */
public class Employee {
    private Long id;
    private String name;
    private String job;
    private Double salary;
    private String hiredate;
    private String address;

    public Employee(){

    }

    public Employee(Long id, String name, String job, Double salary, String hiredate, String address) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.hiredate = hiredate;
        this.address = address;
    }

    public Employee(String name, String job, Double salary, String hiredate, String address) {
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.hiredate = hiredate;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", salary=" + salary +
                ", hiredate='" + hiredate + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
