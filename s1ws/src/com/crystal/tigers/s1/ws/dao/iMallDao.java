package com.crystal.tigers.s1.ws.dao;

import java.util.List;

import com.crystal.tigers.s1.ws.model.Mall;

public interface iMallDao {
	public List<Mall> searchMall(Mall mall);
	public void createMall(Mall mall);	
	public void updateMall(Mall mall);
	public void deleteMall(int mall_id);
	public List<Mall> getMalls();
	public Mall getMallByID(int id);
	
	
	
}
