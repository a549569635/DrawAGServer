package m;

import java.awt.EventQueue;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import frame.ServerFrame;
import frame.WLUpdateFrame;
import warpper.Room;
import warpper.User;

public class Core {
	
	public static ServerFrame serverframe;
	
	public static HashMap<String,User> CLIENT_NAME=new HashMap<String, User>();	
	
	public static ServerRunnable SERVER;
	
	public static HashMap<String,ServerSocketRunnable> CLIENT_SOCKET=new HashMap<String, ServerSocketRunnable>();
	
	public static HashMap<String,Socket> CLIENTS=new HashMap<String, Socket>();
	
	public static HashMap<String,User> NEW_USER=new HashMap<String,User>();
	
	//public static List<String> MSG_SERVER=new ArrayList<String>();

	//public static List<String> MSG_CLIENT=new ArrayList<String>();	
	
	public static HashMap<String,User> CLIENT_USERS=new HashMap<String, User>();
	
	public static List<User> ONLINE_USER=new ArrayList<User>();
	
	public  static List<Room> ONLINE_Room=new ArrayList<Room>();
	
	public  static List<Room> CLIENT_ONLINE_Room=new ArrayList<Room>();

	public static User USER;
	
	public static Room ROOM=null;

	//public static List<QQMsg> MSG_LIST_SERVER=new ArrayList<QQMsg>();

	//public static List<QQMsg> MSG_LIST_CLIENT=new ArrayList<QQMsg>();

	//public static int GAMELIFE1=0;
	//public static int GAMELIFE2=0;
	
	//public static HashMap<String, VSGameData> VS_GAMEDATA=new HashMap<String, VSGameData>();

	//public static VSGameData VS_DATA;
	
	//public static List<GameData> GAME_DATA=new ArrayList<GameData>();
	
	//public static GameData CLIENT_GAME_DATA;
	
	public static ObjectInputStream OBJIN;
	
	public static boolean ISSTART=true;
	
	public static int SCORE=0;
	
	public static List<String> FORBINUSER=new ArrayList<String>();
	
	public static List<String>  DELETEUSER=new ArrayList<String>();

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
