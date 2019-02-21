package net.socket;

import java.net.*;

import com.util.Log;

import java.io.*;

public class ServerThread extends Thread {
	Socket sk;
    BufferedReader br=null;
    private int index;
    public ServerThread(Socket sk,int index){
    	this.sk= sk;
    	this.index=index;
    }
    @Override
    public void run() {
    	Log.log("���������"+index);
    	sendMsg("welcome:"+index);
    	while(true)
    	{
    		try{
    			br  = new BufferedReader(new InputStreamReader(sk.getInputStream()));
    			try{
    				String line = br.readLine();
    				if(line.isEmpty())
    				{
    					Log.log("���ӶϿ���");
    				}
    				Log.log("���Կͻ���"+index+"�����ݣ�"+line+"\n");
    				sendMsg(line);
    			}
    			catch(Exception e){
        			//e.printStackTrace();
    				Log.log("�ͻ���"+index+"�Ͽ�����");
        			break;
        		}
    		}
    		catch(IOException e){
    			e.printStackTrace();
    			break;
    		}
    	}
    }
    /*
     * ���ͻ��˷�����Ϣ
     */
    public void sendMsg(String msg)
    {
    	PrintWriter pout = null;
        try {
            pout = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(sk.getOutputStream())),true);
            pout.println(msg);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * �Ͽ��ͻ��˵�����
     */
    public void closeConnect()
    {
    	try{
            br.close();
    		sk.close();
    	}
    	catch(IOException e){
            e.printStackTrace();
        }
    }
}
