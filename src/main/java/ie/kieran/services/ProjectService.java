package ie.kieran.services;

import java.util.List;

import ie.kieran.domain.Project;

public interface ProjectService {
	void save(Project project);
	Project getByName(String name);
	Project getById(int id);
	List<Project> all();
	Project getOne(int id);
	List<Project> myProjects(int id);
	List<Project> getActive(boolean enabled);
}
