package m;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import warpper.*;
import dbmanager.*;

public class ServerSocketRunnable implements Runnable {
	private Socket soc;//�����׽���
	private ObjectOutputStream objOut;//д������
	private ObjectInputStream objIn;//��ȡ����
	private User user=null;//�����û�����
	private String num=null;
	private UserManager uman;
	
	public ServerSocketRunnable(Socket soc){
		this.soc=soc;
	}

	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		try {
			//��ͻ���д������
			objOut = new ObjectOutputStream(soc.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {//��ȡ����
			objIn = new ObjectInputStream(soc.getInputStream());
			Core.OBJIN=objIn;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while(true){
			try {
				Protocol data=(Protocol)objIn.readObject();
System.out.println("��¼�������յ�");
				if(data.getPro()==1){
					uman=new UserManager();
					user=(User)data.getObj();
					user=uman.query(user.getID(),user.getPassword());
					if(user!=null){
						boolean b=true;
						try {
							for(User us:Core.CLIENT_NAME.values()){
								if(user.getID().equals(us.getID())){
									objOut.writeObject(new Protocol(25,null));	
									objOut.flush();
									b=false;
									break;
								}
							}
						} catch (Exception e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
						if(b){
							user.setAddress(soc.getRemoteSocketAddress().toString());
							objOut.writeObject(new Protocol(1,user));	
							objOut.flush();
							Core.CLIENT_NAME.put(soc.getRemoteSocketAddress().toString(), user);
						}					

					}
					else{						
						objOut.writeObject(new Protocol(20,"123"));
						try {
							Thread.sleep(6000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					List<User> list=new ArrayList<User>(Core.CLIENT_NAME.values());
					Protocol data1=new Protocol(3, list);	
					objOut.flush();
					//for(ServerSocketRunnable Client_A:Core.CLIENT_SOCKET.values()){
					//Client_A.SendMsg(data1);
					//client.SendMsg(dataRoom);
					//}

					//List<Room> room_list=Room.copyList(Core.ONLINE_Room);
					//Protocol dataRoom=new Protocol(4, room_list);
					//ѭ���������������û��̶߳��󣬲����������û�����
					//for(ServerSocketRunnable room_client:Core.CLIENT_SOCKET.values()){
					//System.out.println("���ͷ�����Ϣ");
					//room_client.SendMsg(dataRoom);  	
					//objOut.flush();
					//}
				} else if(data.getPro()==2){
					uman=new UserManager();
					user=(User)data.getObj();
//System.out.println(user.getNickname());
					if(uman.add(user)){
						objOut.writeObject(new Protocol(2,null));
					} else{
						objOut.writeObject(new Protocol(21,null));
					}

				}
			} catch (Exception e) {
				Core.CLIENT_SOCKET.remove(soc.getRemoteSocketAddress().toString());
				Core.CLIENT_USERS.remove(soc.getRemoteSocketAddress().toString());
				Core.CLIENT_SOCKET.remove(soc.getRemoteSocketAddress().toString());
				Core.CLIENT_NAME.remove(soc.getRemoteSocketAddress().toString());
				//e.printStackTrace();
				Core.serverframe.info.append("һ���ͻ����ѶϿ����ӣ���ipΪ��"+soc.getRemoteSocketAddress().toString().replace("/","")+"\n");
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
