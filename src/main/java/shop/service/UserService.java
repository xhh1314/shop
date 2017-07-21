package shop.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import shop.bean.User;

public interface UserService {
	
	public void insert(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	public void update(User user);
	public User selectByEmail(String email);
	public User selectByUuid(String uuid);
	public boolean login(String name,String password,String verificationCode);
	public boolean register(User user);
	public boolean verificationUser(User user);

}
