package m;

import java.io.BufferedInputStream;
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
	private Boolean running = false;
	
	public ServerSocketRunnable(Socket soc){
		this.soc=soc;
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
System.out.println("13");
			objOut = new ObjectOutputStream(soc.getOutputStream());
System.out.println("456");
			objIn = new ObjectInputStream(new BufferedInputStream(soc.getInputStream()));
			//Core.OBJIN=objIn;
			running = true;
System.out.println("huwudanxianchenchuanjianchengong");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			running = false;
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
							for(User us:Core.ONLINE_USER.values()){
								if(user.getID().equals(us.getID())){
									objOut.writeObject(new Protocol(25,null));
									//objOut.flush();
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
							//objOut.flush();
							Core.ONLINE_USER.put(soc.getRemoteSocketAddress().toString(), user);
							SendMsg(new Protocol(5, new ArrayList<Room>(Core.ONLINE_ROOM)));
							
							Protocol data1=new Protocol(3, new ArrayList<User>(Core.ONLINE_USER.values()));
		         			for(ServerSocketRunnable client:Core.CLIENT_RUNNABLE.values()){
		         				client.SendMsg(data1);
		         			}
						}					

					} else{						
						objOut.writeObject(new Protocol(20,"123"));
						//objOut.flush();
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else if(data.getPro()==2){
					uman=new UserManager();
					user=(User)data.getObj();
//System.out.println(user.getNickname());
					if(uman.add(user)){
						objOut.writeObject(new Protocol(2,null));
						//objOut.flush();
						
					} else{
						objOut.writeObject(new Protocol(21,null));
						//objOut.flush();
					}

				}
			} catch (Exception e) {
				running = false;
				Core.CLIENT_SOCKET.remove(soc.getRemoteSocketAddress().toString());
				Core.CLIENT_RUNNABLE.remove(soc.getRemoteSocketAddress().toString());
				Core.ONLINE_USER.remove(soc.getRemoteSocketAddress().toString());
				
				Protocol data1=new Protocol(3, new ArrayList<User>(Core.ONLINE_USER.values()));
     			for(ServerSocketRunnable client:Core.CLIENT_RUNNABLE.values()){
     				client.SendMsg(data1);
     			}
				e.printStackTrace();
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
