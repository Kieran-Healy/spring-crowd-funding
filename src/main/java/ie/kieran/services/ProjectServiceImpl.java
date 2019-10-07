package ie.kieran.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.kieran.dao.ProjectDao;
import ie.kieran.domain.Project;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	ProjectDao projectDao;

	@Override
	public void save(Project project) {
		projectDao.save(project);
	}

	@Override
	public Project getByName(String name) {
		Project project = projectDao.findByProjectName(name);
		if(project != null) {
			return project;
		}
		return null;
	}

	@Override
	public Project getById(int id) {
		if (projectDao.existsById(id))
			return projectDao.findById(id).get();;
		return null;
	}

	@Override
	public List<Project> all() {
		return projectDao.findAll();
	}
	
	@Override
	public Project getOne(int id) {
		return projectDao.getOne(id);
	}
	
	@Override
	public List<Project> myProjects(int id) {
		return projectDao.findByFounder_UserId(id);
	}
	
	@Override
	public List<Project> getActive(boolean enabled) {
		return projectDao.findByEnabled(enabled);
	}
}
