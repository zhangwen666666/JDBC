import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class JDBCInsert{
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		try{
			//注册驱动
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			//DriverManager.registerDriver(driver);
			
			//获取连接
			String url = "jdbc:mysql://192.168.1.14:3306/jdbc";
			String user = "root";
			String password = "1234";
			conn = DriverManager.getConnection(url,user,password);
			
			//创建数据库操纵对象
			stmt = conn.createStatement();
			
			//执行sql语句
			String sql = "insert into t_user(name,password,realname,gender,tel) values('lisi','abcd','李四','女','12345678175')";
			int count = stmt.executeUpdate(sql);
			System.out.println(count>0?"插入成功":"插入失败");
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			//释放资源
			if(stmt!=null){
				try{
					stmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
}