package dbmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import warpper.User;
import dao.UserDao;

public class UserManager{
	DBConnection dbcon = new DBConnection();

	public boolean add(User use) {
		// TODO �Զ����ɵķ������
//System.out.println("������ӵ����ݿ�");		
		return dbcon.update("insert into users(id,password,nickname,email,level,exp) values(?,?,?,?,?,?)",
						use.getID(), use.getPassword(), use.getNickname(),use.getEmail(),
						String.valueOf(use.getLevel()),String.valueOf(use.getExp())) > 0;
	}

	public boolean update(User use) {
		// TODO �Զ����ɵķ������
		return false;
	}

	public boolean delete(int userId) {
		// TODO �Զ����ɵķ������
		return false;
	}

	public User queryById(int userId) {
		// TODO �Զ����ɵķ������
		return null;
	}

	public List<User> queryAll() {
		// TODO �Զ����ɵķ������
		return null;
	}

	public User query(String ID, String Password) {
		// TODO �Զ����ɵķ������
		User use = null;
		ResultSet rs = dbcon.query(
				"select * from users where ID=? and Password=?",ID, Password);
		try {
			while (rs.next()) {
				use = new User(rs.getString("id"), rs.getString("password"), 
						rs.getString("nickname"), rs.getString("email"), 
						rs.getInt("level"), rs.getInt("exp"), rs.getString("hppath"));
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
