/*
	使用JDBC程序向t_user表中插入一条数据
*/
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class JDBCTest01{
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		try{
			//1.注册驱动
			// com.mysql.cj.jdbc.Driver是MySQL驱动最核心的类
			//这个类实现了 java.sql.Driver接口
			Driver driver = new com.mysql.cj.jdbc.Driver();//创建核心驱动
			DriverManager.registerDriver(driver);//注册驱动
			
			//2.获取连接
			String url = "jdbc:mysql://192.168.1.14:3306/jdbc";
			String user = "root";
			String password = "1234";
			conn = DriverManager.getConnection(url,user,password);
			System.out.println("conn = " + conn);
			
			//3.获取数据库操作对象
			stmt = conn.createStatement();
			System.out.println("stmt = " + stmt);
			
			// Statement stmt2 = conn.createStatement();
			// System.out.println("stmt2 = " + stmt2);
			
			//4.执行SQL语句
			String sql = "insert into t_user(name,password,realname,gender,tel) value('wangwu','123','王五','女','12345678901')";
			// boolean isSuccess = stmt.execute(sql);
			
			int count = stmt.executeUpdate(sql);
			System.out.println(count > 0? "插入成功": "插入失败");
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			//6.释放资源
			//关闭前最好判断一下是否为空
			if(stmt != null){
				try{
					stmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
}