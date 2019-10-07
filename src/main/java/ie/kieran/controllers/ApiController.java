package ie.kieran.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.kieran.domain.Project;
import ie.kieran.services.ProjectService;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	ProjectService projectService;
	
	@GetMapping("active")
	public List<Project> getActiveProjects() {
		List<Project> list = projectService.getActive(true);
		return list;
	}
	
	@GetMapping("projects/{id}")
	public List<Project> getUsersProjects(@PathVariable int id) {
		List<Project> list = projectService.myProjects(id);
		return list;
	}

}
