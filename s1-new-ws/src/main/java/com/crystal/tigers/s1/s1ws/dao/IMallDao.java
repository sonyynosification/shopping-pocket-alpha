package com.crystal.tigers.s1.s1ws.dao;

import java.util.List;

import com.crystal.tigers.s1.s1ws.dbmodels.Mall;

public interface IMallDao {
	public List<Mall> searchMall(Mall mall);
	public void createMall(Mall mall);	
	public void updateMall(Mall mall);
	public void deleteMall(int mall_id);
	public List<Mall> getMalls();
	public Mall getMallByID(int id);
	
	
	
}
