package com.crystal.tigers.s1.ws.service;

import java.util.List;

import com.crystal.tigers.s1.ws.model.Store;

public interface StoreService {
	public List<Store> searchStore(Store store);
	public void createStore(Store store);	
	public void updateStore(Store store);
	public void deleteStore(int store_id);
	public List<Store> getStores();
	public Store getStoreByID(int id);
}
