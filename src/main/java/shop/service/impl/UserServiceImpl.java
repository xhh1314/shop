package shop.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import shop.bean.User;
import shop.dao.UserDao;
import shop.service.UserService;
import shop.util.GetMD5;
import shop.util.GetUUID;

@Service("userService")
@Scope("singleton")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Override
	public void insert(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		user.setUuid(GetUUID.getUuid());
		user.setPassword(GetMD5.Md5(user.getPassword()));//MD5加密下
		userDao.insert(user);

	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User selectByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.findByEmail(email);
	}

	@Override
	public User selectByUuid(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(String name, String password, String verificationCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		boolean flag=false;
		
		try {
			insert(user);
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			flag=false;
			throw new RuntimeException(e);
		}
		return flag;
	}

	@Override
	public boolean verificationUser(User user) {
		// TODO Auto-generated method stub
		if(user==null)
			return false;
		User user1=userDao.findByEmail(user.getEmail());
		if(user1==null)
			return false;
		try {
			//验证密码是否符合
			if(user1.getPassword().equals(GetMD5.Md5(user.getPassword())))
				return true;
			else
				return false;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			 throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}

}
