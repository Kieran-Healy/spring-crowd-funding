package ie.kieran.controllers;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping(value= {"/", "/index"})
	public String handleIndexRequest(Locale locale)
	{
		return "index";
	}

}
