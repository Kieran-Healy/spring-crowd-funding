package ie.kieran.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.kieran.domain.Project;
import ie.kieran.domain.User;
import ie.kieran.services.ProjectService;
import ie.kieran.services.UserService;

@Controller
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	UserService userService;
	
	private int currentId;
	
	@GetMapping("/newProject")
	public String handleNewProject(Locale locale, Model model) {
		model.addAttribute("project", new Project());
		return "newProject";
	} 
	
	@PostMapping("/newProject")
	public String handleNewProject(@Valid Project project, BindingResult binding, RedirectAttributes redirectAttributes) {
		if (binding.hasErrors())
			return "newProject";
		if (projectService.getByName(project.getProjectName()) != null)
		{
			redirectAttributes.addFlashAttribute("duplicate", true);
			return "redirect:/newProject";
		}
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user instanceof UserDetails && project.getProjectTarget() > 0) {
			User current = userService.getByName(((UserDetails) user).getUsername());
			project.setFounder(current);
			project.setEnabled(true);
			project.setStartDate(new Date(System.currentTimeMillis()));
			projectService.save(project);
		//	System.out.println(project);
			return "redirect:/project/"+project.getProjectId();
		}
		return "redirect:/newProject";
	} 
	
	@GetMapping("/project/{id}")
	public String getProjectById(@PathVariable int id, Model model, Locale locale) {
		System.out.println(projectService.getById(id));
		currentId = id;
		model.addAttribute("project", projectService.getById(id));
		return "project";
	}
	
	@GetMapping("/myProjects")
	public String getProjectById(Model model, Locale locale) {
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user instanceof UserDetails) {
			User current = userService.getByName(((UserDetails) user).getUsername());
			List<Project> list = projectService.myProjects(current.getUserId());
			model.addAttribute("projects", list);
		}
		return "myProjects";
	}
	
	@GetMapping("/project/{id}/edit")
	public String editProject(@PathVariable int id,Model model, Locale locale) {
		Project project = projectService.getById(id);
		currentId = id;
		model.addAttribute("project", project);
		return "edit";
	}
	
	@PostMapping("/project/{id}/edit")
	public String editProject(@Valid Project project, BindingResult binding, RedirectAttributes redirectAttributes) {
		Project notUpdated = projectService.getOne(currentId);
		notUpdated.setProjectDesc(project.getProjectDesc());
		projectService.save(notUpdated);
		return "redirect:/myProjects";
	}

}
