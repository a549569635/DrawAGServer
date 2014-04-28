package warpper;

import java.awt.Image;
import java.io.Serializable;

public class User implements Serializable, Cloneable {
	private String HPPath = null;
	private String Nickname = null;
	private String ID = null;
	private String Password = null;
	private String Email = null;
	private int Level = 0;
	private int Exp = 0;
	private String Address;
	private String IP;
	private int Port;

	public User(String ID,String Password){
		this.setID(ID);
		this.setPassword(Password);
	}
	
	public User(String ID,String Password,String Nickname,String Email){
		this.setID(ID);
		this.setPassword(Password);
		this.setNickname(Nickname);
		this.setEmail(Email);
	}
	
	//public User(String ID,String Password,String HPPath){
		//this.setID(ID);
		//this.setPassword(Password);
		//this.HPPath = HPPath;
	//}
	
	//public User(String ID,String Password,String Email){
		//this.setID(ID);
		//this.setPassword(Password);
		//this.setEmail(Email);
	//}
	
	public User(String ID,String Password,int Level,int Exp){
		this.setID(ID);
		this.setPassword(Password);
		this.Level = Level;
		this.Exp = Exp;
	}
	
	public User(String ID,String Password,String Nickname,String Email,int Level,int Exp,String HPPath){
		this.setID(ID);
		this.setPassword(Password);
		this.setNickname(Nickname);
		this.Level = Level;
		this.Exp = Exp;
		this.HPPath = HPPath;
	}

	public User() {
		// TODO 自动生成的构造函数存根
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getNickname() {
		return Nickname;
	}

	public void setNickname(String nickname) {
		Nickname = nickname;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public int getExp() {
		return Exp;
	}

	public void setExp(int exp) {
		Exp = exp;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
		String str=address.replace("/", "");
		String[] strs=str.split(":");
		IP=strs[0];
		Port=Integer.valueOf(strs[1]);
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public int getPort() {
		return Port;
	}

	public void setPort(int port) {
		Port = port;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		User user=(User) super.clone();
		return user;
	}

}
