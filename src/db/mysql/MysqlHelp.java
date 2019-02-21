package db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.config.Config;
import com.util.Log;

public class MysqlHelp {

	private static Connection connect=null;
	private static Statement stmt=null;
	/*
	 * 连接数据库
	 */
	public static void ConnectMysql()
	{
		Log.log("打开数据库连接");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//加载 MYSQL JDBC驱动程序
			//Class.forName("com.mysql.jdbc.Driver");//加载 MYSQL JDBC驱动程序
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Log.log("Loading mysql driver error!");
			e.printStackTrace();
		}
		try {
			String address="jdbc:mysql://"+Config.db_address+":"+Config.db_port+"/"+Config.db_name+"?serverTimezone=UTC";
			connect=DriverManager.getConnection(address,Config.db_username,Config.db_psw);//连接mysql服务器
			stmt=connect.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.log("Link mysql database error!");
			e.printStackTrace();
		}
	}
	/*
	 * 获取数据库状态
	 */
	public static Statement getMysqlStatement()
	{
		return stmt;
	}
	/*
	 * 关闭数据库连接
	 */
	public static void closeMysql()
	{
		if(connect==null || stmt==null)
		{
			return;
		}
		try {
			Log.log("断开数据库连接");
			stmt.close();
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
