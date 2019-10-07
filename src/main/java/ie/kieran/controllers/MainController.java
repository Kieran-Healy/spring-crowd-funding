package ie.kieran.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ie.kieran.services.ProjectService;

@Controller
public class MainController {
	
	@Autowired
	ProjectService projectService;
	
	@GetMapping(value= {"/", "/index"})
	public String handleIndexRequest(Locale locale, Model model)
	{
		model.addAttribute("projects", projectService.all());
		return "index";
	}

}
