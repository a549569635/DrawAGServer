package m;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import warpper.Protocol;
import warpper.Room;
import warpper.User;

public class ServerRoomRunnable implements Runnable {
	private DataOutputStream dos;
	private DataInputStream dis;
	
	private Room room;
	private Boolean playing = false;

	public ServerRoomRunnable(Room room) {
		super();
		this.room = room;
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true){
			if(!playing && !Core.ONLINE_ROOM.get(room.getID()).equals(room)){
				this.room = Core.ONLINE_ROOM.get(room.getID());
			}
			
			if(!playing && room.getUserNum()<0){
				Core.ONLINE_ROOM.remove(room.getID());
System.out.println("移除房间"+room.getID());
				Core.ROOM_RUNNABLE.remove(room.getID());
				break;
			}
			
			if(!playing){
				Boolean b = true;
				for(User use : room.getUser()){
					//b = use.getReadyed();
				}
				if(b){
					startPlay();
				}
			}
			
			
		}

	}

	private void startPlay() {
		// TODO 自动生成的方法存根
		
	}

	public void updateRoom() {
		// TODO 自动生成的方法存根
		for(User use : room.getUser()){
			Core.CLIENT_RUNNABLE.get(use.getAddress()).SendMsg(new Protocol(5,Core.ONLINE_ROOM.get(room.getID())));
		}
	}

}
