package m;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import warpper.*;
import dbmanager.*;

public class ServerSocketRunnable implements Runnable {
	private Socket soc;
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;
	private User user=null;
	private String num=null;
	private UserManager uman;
	
	public ServerSocketRunnable(Socket soc){
		this.soc=soc;
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			objOut = new ObjectOutputStream(new BufferedOutputStream(soc.getOutputStream()));
System.out.println("13");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			objIn = new ObjectInputStream(new BufferedInputStream(soc.getInputStream()));
System.out.println("456");
			//Core.OBJIN=objIn;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while(true){
			try {
				Protocol data=(Protocol)objIn.readObject();
System.out.println("登录数据已收到");
				if(data.getPro()==1){
					uman=new UserManager();
					user=(User)data.getObj();
					user=uman.query(user.getID(),user.getPassword());
					if(user!=null){
						boolean b=true;
						try {
							for(User us:Core.ONLINE_USERS.values()){
								if(user.getID().equals(us.getID())){
									objOut.writeObject(new Protocol(25,null));
									objOut.flush();
									b=false;
									break;
								}
							}
						} catch (Exception e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
						if(b){
							user.setAddress(soc.getRemoteSocketAddress().toString());
							objOut.writeObject(new Protocol(1,user));	
							objOut.flush();
							Core.ONLINE_USERS.put(soc.getRemoteSocketAddress().toString(), user);
						}					

					}
					else{						
						objOut.writeObject(new Protocol(20,"123"));
						objOut.flush();
						try {
							Thread.sleep(6000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					ArrayList<User> list=new ArrayList<User>(Core.ONLINE_USERS.values());
					Protocol data1=new Protocol(3, list);
					
				} else if(data.getPro()==2){
					uman=new UserManager();
					user=(User)data.getObj();
//System.out.println(user.getNickname());
					if(uman.add(user)){
						objOut.writeObject(new Protocol(2,null));
						objOut.flush();
						
					} else{
						objOut.writeObject(new Protocol(21,null));
						objOut.flush();
					}

				}
			} catch (Exception e) {
				Core.CLIENT_SOCKET.remove(soc.getRemoteSocketAddress().toString());
				Core.CLIENT_RUNNABLE.remove(soc.getRemoteSocketAddress().toString());
				Core.ONLINE_USERS.remove(soc.getRemoteSocketAddress().toString());
				//e.printStackTrace();
				Core.serverframe.info.append("一个客户端已断开连接，其ip为："+soc.getRemoteSocketAddress().toString().replace("/","")+"\n");
				break;
			}
		}	

	}

	public void SendMsg(Protocol data){
		try {
			objOut.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
