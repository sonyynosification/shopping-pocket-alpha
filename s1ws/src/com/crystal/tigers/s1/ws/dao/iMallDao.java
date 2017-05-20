package com.crystal.tigers.s1.ws.dao;

import java.util.List;

import com.crystal.tigers.s1.ws.model.Mall;

public interface iMallDao {
	public List<Mall> searchStore(Mall mall);
	public void createStore(Mall mall);	
	public void updateStore(Mall mall);
	public void deleteStore(int mall_id);
	public List<Mall> getMalls();
	public Mall getMallByID(int id);
	
	
	
}
