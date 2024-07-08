import java.sql.Connection;
import java.sql.Statement;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest02{
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		try{
			//注册驱动
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
		
			//测试连接
			String url = "jdbc:mysql://192.168.1.14:3306/jdbc";
			String user = "root";
			String password = "1234";
			conn = DriverManager.getConnection(url,user,password);
			//获取数据库操作对象
			stmt = conn.createStatement();
		
			String sql = "delete from t_user where realname='王五'";
			//执行sql语句
			int count = stmt.executeUpdate(sql);
			System.out.println(count > 0? "删除成功":"删除失败");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
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