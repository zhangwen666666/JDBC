package com.jdbc.dao;

import com.jdbc.utils.DbUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 最基础的DAO，所有的DAO都应该继承这个DAO
 */
public class BaseDao {
    /**
     * 负责执行 insert delete update 类型的sql语句的方法
     *
     * @param sql    要执行的sql语句
     * @param params 给sql语句传的参数
     * @return 影响的记录条数
     */
    public int executeUpdate(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            //获取连接
            connection = DbUtils.getConnection();
            //获取预编译的数据库操作对象
            ps = connection.prepareStatement(sql);
            //给?传值
            if (params != null && params.length > 0) {
                for (int i = 1; i <= params.length; i++) {
                    ps.setObject(i, params[i - 1]);
                }
            }
            //执行SQL语句
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(connection, ps, null);
        }
        return count;
    }

    /**
     * 通用的负责执行select语句的方法
     *
     * @param sql    要执行的sql语句
     * @param c      查询的对象的类型
     * @param params 给sql语句传的参数
     * @param <T>    查询的对象的类型(List集合中元素的类型)
     * @return 查询的结果集（List集合）
     */
    public <T> List<T> executeQuery(Class<T> c, String sql, Object... params) {
        List<T> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //获取连接
            connection = DbUtils.getConnection();
            //获取预编译的数据库操作对象
            preparedStatement = connection.prepareStatement(sql);
            //给?传值
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            //执行SQL语句
            resultSet = preparedStatement.executeQuery();
            //获取查询结果集元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            //获取列数
            int columnCount = metaData.getColumnCount();

            //处理结果集
            while (resultSet.next()) {
                //创建对象
                T obj = c.newInstance();

                //给对象赋值
                for (int i = 1; i <= columnCount; i++) {
                    //1.获取列名(这里列名要与封装的Java对象的属性名相同,这是通过限制sql语句实现的)
                    //  数据库中的列名可以与属性名不相同，那么查询的sql语句就需要对列名起别名
                    //  总之需要保证sql语句的列名与Java对象的列名一致
                    String fieldName = metaData.getColumnName(i);
                    // 获取属性
                    Field field = c.getDeclaredField(fieldName);
                    // 打破封装
                    field.setAccessible(true);
                    // 赋值
                    field.set(obj,resultSet.getObject(fieldName));
                }
                //将对象添加到集合中
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.close(connection, preparedStatement, resultSet);
        }
        return list;
    }


    public <T> T executeQueryOne(Class<T> c, String sql, Object... params) {
        List<T> list = executeQuery(c,sql,params);
        if(list == null || list.isEmpty()){
            return null;
        }
        return list.getFirst();
    }
}
