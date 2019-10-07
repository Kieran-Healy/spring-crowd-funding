package ie.kieran.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.kieran.domain.Project;

public interface ProjectDao extends JpaRepository<Project, Integer> {
	
	Project findByProjectName(String projectName);
	List<Project> findAllByOrderByProjectNameAsc();
	List<Project> findByFounder_UserId(int id);
	List<Project> findByEnabled(boolean enabled);
}
