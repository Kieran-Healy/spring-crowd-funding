package ie.kieran.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.kieran.dao.UserDao;
import ie.kieran.domain.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public User getByName(String name) {
		User user = userDao.findByUserEmail(name);
		if(user != null) {
			return user;
		}
		return null;
	}

}
