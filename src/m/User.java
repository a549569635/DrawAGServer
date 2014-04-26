package m;

import javax.swing.ImageIcon;

public class User {
	private ImageIcon HeadP;
	private String Nickname;
	private String ID;
	private String Password;
	private String Email;
	private int Level;
	private int Exp;
	
	public User(String ID,String Password,String Nickname,String Email){
		this.ID = ID;
		this.Password = Password;
		this.Nickname = Nickname;
		this.Email = Email;
	}
	
	public User(String ID,String Password,ImageIcon HeadP){
		this.ID = ID;
		this.Password = Password;
		this.HeadP = HeadP;
	}
	
	public User(String ID,String Password,String Email){
		this.ID = ID;
		this.Password = Password;
		this.Email = Email;
	}
	
	public User(String ID,String Password,int Level,int Exp){
		this.ID = ID;
		this.Password = Password;
		this.Nickname = Nickname;
	}
	
	public User(String ID,String Password,String Nickname,String Email,int Level,int Exp){
		this.ID = ID;
		this.Password = Password;
		this.Nickname = Nickname;
	}

	public User() {
		// TODO �Զ����ɵĹ��캯�����
	}

}
