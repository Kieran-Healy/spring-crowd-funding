package ie.kieran.services;

import ie.kieran.domain.User;

public interface UserService {
	void save(User user);
	User getByName(String name);

}
