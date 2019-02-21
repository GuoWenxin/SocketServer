package db.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.Log;

public class MysqlTest {

	private Statement stmt;
	public MysqlTest(Statement stmt)
	{
		this.stmt=stmt;
	}
	public void showData()
	{
		String query="select * from user";
		try {
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				Log.log(rs.getString("username"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.log("get date error!");
			e.printStackTrace();
		}
	}
}
