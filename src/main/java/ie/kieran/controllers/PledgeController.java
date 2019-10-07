package ie.kieran.controllers;

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

import ie.kieran.domain.Pledge;
import ie.kieran.domain.Project;
import ie.kieran.domain.User;
import ie.kieran.services.PledgeService;
import ie.kieran.services.ProjectService;
import ie.kieran.services.UserService;

@Controller
public class PledgeController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PledgeService pledgeService;
	
	private int currentId;
	
	@GetMapping("/project/{id}/pledge")
	public String handleNewPledge(@PathVariable int id, Locale locale, Model model) {
		Pledge pledge = new Pledge();
		currentId = id;
		model.addAttribute("pledge", pledge);
		return "pledge";
	} 
	
	@PostMapping("/project/{id}/pledge")
	public String handleNewPledge(@Valid Pledge pledge, BindingResult binding, RedirectAttributes redirectAttributes) {
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Project project = projectService.getOne(currentId);
		project.setCurrentAmount(pledge.getAmount() + project.getCurrentAmount());
		if(user instanceof UserDetails) {
			User current = userService.getByName(((UserDetails) user).getUsername());
			if(project.getEnabled() == true && current.getUserId() != project.getFounder().getUserId()) {
				if(project.getCurrentAmount() >= project.getProjectTarget()) {
					project.setEnabled(false);
				}
				pledge.setProject(project);
				pledge.setUser(current);
				projectService.save(project);
				pledgeService.save(pledge);
				System.out.println(pledge);
				return "redirect:/project/"+currentId;
			}
			
		}
		return "redirect/project/"+currentId;
	}

}
