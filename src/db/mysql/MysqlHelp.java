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
	 * �������ݿ�
	 */
	public static void ConnectMysql()
	{
		Log.log("�����ݿ�����");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//���� MYSQL JDBC��������
			//Class.forName("com.mysql.jdbc.Driver");//���� MYSQL JDBC��������
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Log.log("Loading mysql driver error!");
			e.printStackTrace();
		}
		try {
			String address="jdbc:mysql://"+Config.db_address+":"+Config.db_port+"/"+Config.db_name+"?serverTimezone=UTC";
			connect=DriverManager.getConnection(address,Config.db_username,Config.db_psw);//����mysql������
			stmt=connect.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.log("Link mysql database error!");
			e.printStackTrace();
		}
	}
	/*
	 * ��ȡ���ݿ�״̬
	 */
	public static Statement getMysqlStatement()
	{
		return stmt;
	}
	/*
	 * �ر����ݿ�����
	 */
	public static void closeMysql()
	{
		if(connect==null || stmt==null)
		{
			return;
		}
		try {
			Log.log("�Ͽ����ݿ�����");
			stmt.close();
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
