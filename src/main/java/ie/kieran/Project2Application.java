package ie.kieran;

import org.h2.command.CommandInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import ie.kieran.dao.RoleDao;
import ie.kieran.dao.UserDao;
import ie.kieran.domain.Role;
import ie.kieran.domain.User;

@SpringBootApplication
public class Project2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Project2Application.class, args);
	}
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void run(String... args) throws Exception {
		
		Role role1 = new Role("kieran.healy2@mycit.ie", "ROLE_ADMIN");
		Role role2 = new Role("test@test.com", "ROLE_API");
		roleDao.save(role1);
		roleDao.save(role2);
		User user1 = new User("kieran.healy2@mycit.ie", passwordEncoder.encode("password"), role1, true);
		User user2 = new User("test@test.com", passwordEncoder.encode("password"), role2, true);
		userDao.save(user1);
		userDao.save(user2);
		System.out.println(user1);
	}
}
