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
			//���ݿ����
			MysqlTest mst=new MysqlTest(MysqlHelp.getMysqlStatement());
			mst.showData();
			
			ss = new ServerSocket(Config.port);//��һ���ܹ���������ͨ��ʵ���������
			Log.log("���������ڵȴ��ͻ��˵���������----");
			//��һ��whileѭ������ͬʱ��Ӧ����ͻ��˵�����
			while(true){
				Socket sk= ss.accept();//������������Ӧ�˿ڵ�����
				ServerThread  st = new ServerThread(sk,index);//����һ���̣߳����̴߳���һ���׽���
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
			Log.log("���������");
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
						Log.log("�رշ�����\n");
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
