package shop.dao;

import shop.bean.User;

public interface UserDao {
	
	public User findByEmail(String email);
	public User findByUuid(String uuid);
	public void insert(User user);
	public void update(User user);

}
