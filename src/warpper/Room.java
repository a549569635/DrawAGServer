package warpper;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5084314616549475353L;
	public static final int STATE_ACCESS = 1;
	public static final int STATE_PLAYING = 0;
	public static final int STATE_FULL = 2;
	
	private int ID;
	private String name;
	public ArrayList<User> user = new ArrayList<User>();
	private int state = 1;
	private int userNum = 0;
	
	public Room(String name){
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<User> getUser(){
		return user;
	}

	public void addUser(User use) {
		if(state == 1 && userNum < 4){
			user.add(use);
			userNum++;
		}
		if(userNum >= 4){
			state = 2;
		}
	}
	
	public void removeUser(int index){
		if(0<=index && userNum>index){
			user.remove(index);
			userNum--;
		}
		if(userNum < 4){
			state = 1;
		}
	}
	
	public void removeUser(User use){
		for(User us : user){
			if(use.getID().equals(us.getID())){
				user.remove(us);
				userNum--;
				break;
			}
		}
		if(userNum < 4){
			state = 1;
		}
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

}
