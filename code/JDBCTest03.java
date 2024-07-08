import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class JDBCTest03{
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		try{
			//1.注册驱动
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			//2.获取连接
			String url = "jdbc:mysql://192.168.1.14:3306/jdbc";
			String user = "root";
			String password = "1234";
			conn = DriverManager.getConnection(url,user,password);
			
			//3. 创建数据库操作对象
			stmt = conn.createStatement();
			
			//4. 执行SQL语句
			String sql = "update t_user set realname='王德发' where id=1";
			int count = stmt.executeUpdate(sql);
			System.out.println(count>0 ? "修改成功":"修改失败");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			//6.释放资源
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