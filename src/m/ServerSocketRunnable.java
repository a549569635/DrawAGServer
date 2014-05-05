package m;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import warpper.*;
import dbmanager.*;

public class ServerSocketRunnable implements Runnable {
	private Socket soc;
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;
	private User user=null;
	private int roomID = 0;
	private UserManager uman;
	private Boolean running = false;
	
	public ServerSocketRunnable(Socket soc){
		this.soc = soc;
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
//System.out.println("13");
			objOut = new ObjectOutputStream(soc.getOutputStream());
//System.out.println("456");
			objIn = new ObjectInputStream(new BufferedInputStream(soc.getInputStream()));
			//Core.OBJIN=objIn;
			running = true;
//System.out.println("huwudanxianchenchuanjianchengong");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			running = false;
		}
		
		while(running){
			try {
				Protocol data = (Protocol)objIn.readObject();
//System.out.println("登录数据已收到");
				if(data.getPro() == 1){
					uman = new UserManager();
					user = (User)data.getObj();
					user = uman.query(user.getID(),user.getPassword());
					if(user != null){
						boolean b = true;
						try {
							for(User us:Core.ONLINE_USER.values()){
								if(user.getID().equals(us.getID())){
									objOut.writeObject(new Protocol(25,null));
									//objOut.flush();
									b = false;
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
							Thread.sleep(500);
							SendMsg(new Protocol(4, Core.ONLINE_ROOM));
							updateUsers();
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
				} else if(data.getPro() == 2){
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

				} else if(data.getPro() == 3){
					user = (User) data.getObj();
					Core.ONLINE_USER.put(user.getAddress(), user);
					Room room = Core.ONLINE_ROOM.get(roomID);
					for(User use : room.getUser()){
						if(use.getID().equals(user.getID())){
							use.setReadyed(user.getReadyed());
						}
					}
					Core.ONLINE_ROOM.put(roomID, room);
					//for(int i=0;i<4;i++){
						//if(Core.ONLINE_ROOM.get(roomID).getUser().get(i).getID().equals(user.getID())){
							//Core.ONLINE_ROOM.get(roomID).getUser().set(i, user);
							//break;
						//}
					//}
System.out.println(Core.ONLINE_ROOM.get(roomID).getUser().get(0).getReadyed());
					//Core.ONLINE_ROOM.put(roomID, room);
					//Core.ROOM_RUNNABLE.get(roomID).updateRoom();
					updateUsers();
					updateRooms();
				} else if(data.getPro() == 5){
					Room room = Core.ONLINE_ROOM.get(((Room) data.getObj()).getID());
					if(room.getState() == Room.STATE_ACCESS){
						room.addUser(user);
						roomID = room.getID();
						Core.ONLINE_ROOM.put(roomID, room);
						SendMsg(new Protocol(6,room));
						updateRooms();
					} else{
						updateRooms();
						SendMsg(new Protocol(22,null));
					}
				} else if(data.getPro() == 6){
					if(Core.ROOM_COUNT < 799){
						Room room = (Room) data.getObj();
						room.setID(Core.ROOM_COUNT);
						Core.ROOM_COUNT++;
						Core.ONLINE_ROOM.put(room.getID(), room);
						ServerRoomRunnable srr = new ServerRoomRunnable(room);
						new Thread(srr).start();
						Core.ROOM_RUNNABLE.put(room.getID(), srr);
						updateRooms();
						SendMsg(new Protocol(6,room));
						roomID = room.getID();
					}else{
						SendMsg(new Protocol(23,null));
						JOptionPane.showMessageDialog(null, "累计房间数已达上限，请重新启动服务器！");
					}
				} else if(data.getPro() == 7){
					try {
						Message msg = (Message) data.getObj();
						if(msg.to.equals("\u6240\u6709\u4EBA")){
							for(ServerSocketRunnable client:Core.CLIENT_RUNNABLE.values()){
								client.SendMsg(data);
							}
						} else{
							for(User use : Core.ONLINE_USER.values()){
								if(use.getID().equals(msg.to)){
									Core.CLIENT_RUNNABLE.get(use.getAddress()).SendMsg(data);
								}
							}
							SendMsg(data);
						}
					} catch (NullPointerException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
						SendMsg(new Protocol(24,null));
					}
				} else if(data.getPro() == 22){
					Core.ONLINE_ROOM.get(roomID).removeUser(user);
					user.setReadyed(false);
					Core.ONLINE_USER.put(user.getAddress(), user);
					roomID = 0;
					updateUsers();
					updateRooms();
				} else if(data.getPro() == 23){
					
				}
			} catch (Exception e) {
				running = false;
				Core.CLIENT_SOCKET.remove(soc.getRemoteSocketAddress().toString());
				Core.CLIENT_RUNNABLE.remove(soc.getRemoteSocketAddress().toString());
				Core.ONLINE_USER.remove(soc.getRemoteSocketAddress().toString());
				if(roomID != 0){
					Core.ONLINE_ROOM.get(roomID).removeUser(user);
				}
				updateUsers();
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
	
	public void updateUsers(){
System.out.println("updateUsers");
		Protocol data = new Protocol(3, new ArrayList<User>(Core.ONLINE_USER.values()));
		for(ServerSocketRunnable client:Core.CLIENT_RUNNABLE.values()){
			client.SendMsg(data);
		}
	}
	
	public void updateRooms(){
System.out.println("updateRooms");
		Protocol data = new Protocol(4, Core.ONLINE_ROOM);
		for(ServerSocketRunnable client:Core.CLIENT_RUNNABLE.values()){
			client.SendMsg(data);
		}
	}

}
