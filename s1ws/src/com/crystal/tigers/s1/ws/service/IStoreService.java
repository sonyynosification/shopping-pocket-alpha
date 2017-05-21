package com.crystal.tigers.s1.ws.service;

import java.util.List;

import com.crystal.tigers.s1.ws.model.Store;

public interface IStoreService {

	public List<Store> searchStore(Store store);

	public List<Store> searchStore(Store store, int maxReturn);

    public List<Store> searchStore(Store store, int maxReturn, int startingIndex);

    //TODO: should we handle the return value? If yes, a meaningful error message can be provided to user
	public void createStore(Store store);

	//TODO: should we handle the return value? If yes, a meaningful error message can be provided to user
	public void updateStore(Store store);

	//TODO: should we handle the return value? If yes, a meaningful error message can be provided to user
	public void deleteStore(int store_id);

	public List<Store> getStores();

	public List<Store> getStores(int maxReturn);

	public Store getStoreByID(int id);

	public boolean exists(Store store);

	public void saveStore(Store newStore);
}
