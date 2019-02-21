package com.main;

import java.net.*;
import java.util.Scanner;

import com.config.Config;
import com.util.Log;

import db.mysql.MysqlHelp;
import db.mysql.MysqlTest;
import net.socket.*;

public class Main {
	static ServerSocket ss;
	public static void main(String[] args)
    {
		int index=0;
		Main main=new Main();
		OperationThread ot=main.new OperationThread();
		ot.start();
		try
		{
			MysqlHelp.ConnectMysql();
			//数据库测试
			MysqlTest mst=new MysqlTest(MysqlHelp.getMysqlStatement());
			mst.showData();
			
			ss = new ServerSocket(Config.port);//是一个能够接受其他通信实体请求的类
			Log.log("服务器正在等待客户端的连接请求----");
			//用一个while循环可以同时响应多个客户端的请求
			while(true){
				Socket sk= ss.accept();//服务器监听对应端口的输入
				ServerThread  st = new ServerThread(sk,index);//创建一个线程，用线程创建一个套接字
				st.start();
				index++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
	class OperationThread extends Thread
	{
		@Override
		public void run()
		{
			Log.log("可输入操作");
			while(true)
			{
				//BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
				Scanner sc = new Scanner(System.in); 
				try {
					//String str = br.readLine();
					String str=sc.nextLine();
					if(str.equals("close"))
					{
						MysqlHelp.closeMysql();
						Log.log("关闭服务器\n");
						ss.close();
						break;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
	}
}
