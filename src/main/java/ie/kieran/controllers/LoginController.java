package ie.kieran.controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping(value = "/login")
	public String login(Locale locale) 
	{
		return "login";
	}
	
	@GetMapping(value = "/403")
	public String preventAccess(Locale locale) 
	{
		return "403";
	}
	
	@GetMapping(value="/logout")
	public String register(HttpSession session)
	{
		session.removeAttribute("user");
		return "index";
	}
	
	@GetMapping(value="/loggedin")
	public void loggedin(HttpSession session)
	{
		if (session.getAttribute("loggedin") == null)
			session.setAttribute("loggedin",  true);
	}
}

