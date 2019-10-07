package ie.kieran.services;

import ie.kieran.domain.Pledge;

public interface PledgeService {
	void save(Pledge pledge);
	Pledge getById(int id);

}
