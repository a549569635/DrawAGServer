package dbmanager;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;

import warpper.User;

public class UserManager{
	DBConnection dbcon = new DBConnection();

	public boolean add(User use) {
		// TODO 自动生成的方法存根
//System.out.println("尝试添加到数据库");
		//if(use.getHP() != null){
			//ImageIO.write(use.getHP(), "PNG", new File("D:/DrawAGServer/data/userhp/"+use.getID()+".png"));
		//}		
		return dbcon.update("insert into users(id,password,nickname,email,level,exp,hppath) values(?,?,?,?,?,?,?)",
						use.getID(), use.getPassword(), use.getNickname(),use.getEmail(), String.valueOf(use.getLevel()),
						String.valueOf(use.getExp()),"D:/DrawAGServer/resource/defaulthp.png") > 0;
	}

	public boolean update(User use) {
		// TODO 自动生成的方法存根
		return false;
	}

	public boolean delete(int userId) {
		// TODO 自动生成的方法存根
		return false;
	}

	public User queryById(int userId) {
		// TODO 自动生成的方法存根
		return null;
	}

	public List<User> queryAll() {
		// TODO 自动生成的方法存根
		return null;
	}

	public User query(String ID, String Password) {
		// TODO 自动生成的方法存根
		User use = null;
		ResultSet rs = dbcon.query(
				"select * from users where ID=? and Password=?",ID, Password);
		try {
			while (rs.next()) {
				use = new User(rs.getString("id"), rs.getString("nickname"), rs.getString("email"), 
						rs.getInt("level"), rs.getInt("exp"), rs.getString("hppath"));
				//try {
					//use.setHP(ImageIO.read(new File(use.getHPPath())));
				//} catch (IOException e) {
					// TODO 自动生成的 catch 块
					//e.printStackTrace();
				//}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbcon.closeAll();
		}
		return use;
	}

}
