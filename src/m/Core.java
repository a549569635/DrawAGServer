package m;

import java.awt.EventQueue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import frame.ServerFrame;
import warpper.*;

public class Core {	
	
	public static ServerRunnable SERVER;
	public static ServerFrame serverframe;
	
    //<getRemoteAddress(),User>在线客户端地址地址和用户对象
	public static volatile HashMap<String,User> ONLINE_USER=new HashMap<String, User>();
	
	//<getRemoteAddress(),Socket>在线客户端地址和套接字对象
	public static volatile HashMap<String,Socket> CLIENT_SOCKET=new HashMap<String, Socket>();
	
	//<getRemoteAddress(),ServerSocketRunnable>在线客户端地址和服务线程对象
	public static volatile HashMap<String,ServerSocketRunnable> CLIENT_RUNNABLE=new HashMap<String, ServerSocketRunnable>();
	
	//房间编号
	public static volatile int ROOM_COUNT = 100;
	
	//<Room.ID,Room>房间ID和房间对象
	public static volatile HashMap<Integer,Room> ONLINE_ROOM = new HashMap<Integer,Room>();
	
	//<Room.ID,ServerRoomRunnable>房间ID和房间服务线程
	public static volatile HashMap<Integer,ServerRoomRunnable> ROOM_RUNNABLE = new HashMap<Integer,ServerRoomRunnable>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					serverframe = new ServerFrame();
					serverframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
