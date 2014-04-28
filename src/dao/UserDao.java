package dao;

import java.util.List;

import warpper.User;

public interface UserDao {
	
	public boolean add(User use);
	
	public boolean update(User use);

	public boolean delete(int userId);

	public User queryById(int userId);

	public List<User> queryAll();
}
