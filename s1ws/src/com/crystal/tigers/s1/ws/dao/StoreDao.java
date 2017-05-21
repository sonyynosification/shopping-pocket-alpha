package com.crystal.tigers.s1.ws.dao;

import java.util.List;

import com.crystal.tigers.s1.ws.common.objects.SearchOrdering;
import com.crystal.tigers.s1.ws.model.Store;
;

public interface StoreDao {
	public List<Store> searchStore(Store store);
	public void createStore(Store store);	
	public void updateStore(Store store);
	public void deleteStore(int store_id);
	public List<Store> getStores();
	public Store getStoreByID(int id);
	
	public List<Store> findStores(Store searchStore, int maxReturn, SearchOrdering ordering);
	
}
