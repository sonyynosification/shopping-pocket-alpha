package com.crystaltiger.dao;

import java.util.List;

import com.crystaltiger.model.Store;
import com.crystaltiger.model.User;;

public interface StoreDao {
	public List<Store> searchStore(Store store);
	public void createStore(Store store);	
	public void updateStore(Store store);
	public void deleteStore(int store_id);
	public List<Store> getStores();
	public Store getStoreByID(int id);
	
	
	
}
