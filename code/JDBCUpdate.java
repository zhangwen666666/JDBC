import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class JDBCUpdate{
	public static void main(String[] args) throws SQLException{
		//1.注册驱动
		Driver driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);
		
		//2.获取连接
		String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
		String user = "root";
		String password = "1234";
		Connection conn = DriverManager.getConnection(url,user,password);
		
		//3.创建数据操作对象
		Statement stmt = conn.createStatement();
		
		//4.执行sql语句
		String sql = "update t_user set realname='桥本有菜' where id=8";
		int count = stmt.executeUpdate(sql);
		System.out.println(count > 0? "更新成功":"更新失败");
		
		//6.释放资源
		stmt.close();
		conn.close();
	}
}