package ie.kieran.controllers;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.kieran.dao.RoleDao;
import ie.kieran.domain.Role;
import ie.kieran.domain.User;
import ie.kieran.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/register")
	public String register(Locale locale, Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@Valid User user, BindingResult binding, RedirectAttributes redirectAttributes) {
		if (binding.hasErrors())
			return "register";
		if (userService.getByName(user.getUserEmail()) != null)
		{
			redirectAttributes.addFlashAttribute("duplicate", true);
			return "redirect:/register";
		}
		Role role = new Role(user.getUserEmail(), "ROLE_USER");
		String notEncoded = user.getUserPassword();
		String encoded = passwordEncoder.encode(notEncoded);
		user.setUserPassword(encoded);
		roleDao.save(role);
		user.setRole(role);
		user.setUserEnabled(true);
		userService.save(user);
		System.out.println(user);
		return "redirect:/login";
	}

}
