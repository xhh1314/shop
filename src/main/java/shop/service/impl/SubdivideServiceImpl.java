package shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import shop.bean.Subdivide;
import shop.dao.SubdivideDao;
import shop.exception.MyException;
import shop.service.SubdivideService;
import shop.util.GetUUID;

@Service("subdivideService")
@Scope("singleton")
public class SubdivideServiceImpl implements SubdivideService {
	
	@Autowired
	 private SubdivideDao subdivideDao;

	@Override
	public boolean insert(Subdivide subdivide) throws MyException, Exception {
		// TODO Auto-generated method stub
		subdivide.setUuid(GetUUID.getUuid());
		subdivideDao.insertSubdivide(subdivide);
		return true;
	}

	@Override
	public List<Subdivide> findAll() {
		// TODO Auto-generated method stub
		return subdivideDao.findAll();
	}

	@Override
	public Subdivide findById(String uuid) {
		// TODO Auto-generated method stub
		return subdivideDao.selectById(uuid);
	}

	@Override
	public boolean update(Subdivide subdivide) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Subdivide subdivide) {
		// TODO Auto-generated method stub
		return false;
	}

}
