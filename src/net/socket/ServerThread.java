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
    	Log.log("新链接入口"+index);
    	sendMsg("welcome:"+index);
    	while(true)
    	{
    		try{
    			br  = new BufferedReader(new InputStreamReader(sk.getInputStream()));
    			try{
    				String line = br.readLine();
    				if(line.isEmpty())
    				{
    					Log.log("链接断开：");
    				}
    				Log.log("来自客户端"+index+"的数据："+line+"\n");
    				sendMsg(line);
    			}
    			catch(Exception e){
        			//e.printStackTrace();
    				Log.log("客户端"+index+"断开连接");
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
     * 给客户端发送消息
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
     * 断开客户端的连接
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
