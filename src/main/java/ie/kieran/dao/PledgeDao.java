package ie.kieran.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.kieran.domain.Pledge;

public interface PledgeDao extends JpaRepository<Pledge, Integer>  {
	
	

}
