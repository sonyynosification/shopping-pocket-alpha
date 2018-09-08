package com.crystal.tigers.s1.s1ws.services;

import java.util.List;

import com.crystal.tigers.s1.s1ws.dbmodels.Mall;

public interface IMallService {

	public List<Mall> searchMall(Mall mall);

	public List<Mall> searchMall(Mall mall, int maxReturn);

    public List<Mall> searchMall(Mall mall, int maxReturn, int startingIndex);

    //TODO: should we handle the return value? If yes, a meaningful error message can be provided to user
	public void createMall(Mall mall);

	//TODO: should we handle the return value? If yes, a meaningful error message can be provided to user
	public void updateMall(Mall mall);

	//TODO: should we handle the return value? If yes, a meaningful error message can be provided to user
	public void deleteMall(int mall_id);

	public List<Mall> getMalls();

	public List<Mall> getMalls(int maxReturn);

	public Mall getMallByID(int id);

	public boolean exists(Mall mall);

	public void saveMall(Mall newMall);
}
