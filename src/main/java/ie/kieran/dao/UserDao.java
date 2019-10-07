package ie.kieran.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ie.kieran.domain.User;

public interface UserDao extends JpaRepository<User, Integer>  {
	
	User findByUserEmail(String userEmail);
	List<User> findAllByOrderByUserEmailAsc();

}
