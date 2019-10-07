package ie.kieran.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.kieran.dao.PledgeDao;
import ie.kieran.domain.Pledge;

@Service
public class PledgeServiceImpl implements PledgeService {

	@Autowired
	PledgeDao pledgeDao;

	@Override
	public void save(Pledge pledge) {
		pledgeDao.save(pledge);
	}

	@Override
	public Pledge getById(int id) {
		if (pledgeDao.existsById(id))
			return pledgeDao.findById(id).get();;
		return null;
	}
}
