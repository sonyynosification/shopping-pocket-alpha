package com.crystal.tigers.s1.ws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crystal.tigers.s1.ws.dao.iMallDao;
import com.crystal.tigers.s1.ws.model.Mall;

@Service("mallService")
@Transactional

public class MallServiceImpl implements IMallService {

	@Autowired
	private iMallDao iMallDao;
	
	@Override
	public List<Mall> searchMall(Mall mall) {
		// TODO Auto-generated method stub
		return iMallDao.searchMall(mall);
	}

	@Override
	public List<Mall> searchMall(Mall mall, int maxReturn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mall> searchMall(Mall mall, int maxReturn, int startingIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createMall(Mall mall) {
		iMallDao.createMall(mall);
		
	}

	@Override
	public void updateMall(Mall mall) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMall(int mall_id) {		
		iMallDao.deleteMall(mall_id);
		
	}

	@Override
	public List<Mall> getMalls() {
		// TODO Auto-generated method stub
		return iMallDao.getMalls();
	}

	@Override
	public List<Mall> getMalls(int maxReturn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mall getMallByID(int id) {
		// TODO Auto-generated method stub
		return iMallDao.getMallByID(id);
	}

	@Override
	public boolean exists(Mall mall) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void saveMall(Mall newMall) {
		// TODO Auto-generated method stub
		
	}

}
