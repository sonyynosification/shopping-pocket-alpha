package com.crystaltiger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crystaltiger.dao.StoreDao;
import com.crystaltiger.model.Store;
@Service("storeService")
@Transactional
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreDao storeDao; 
	@Override
	public List<Store> searchStore(Store store) {		
		return storeDao.searchStore(store);
	}
	@Override
	public List<Store> getStores() {
		// TODO Auto-generated method stub
		return storeDao.getStores();
	}
	@Override
	public void createStore(Store store) {
		storeDao.createStore(store);
		
	}
	@Override
	public void updateStore(Store store) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteStore(int store_id) {
		storeDao.deleteStore(store_id);
		
	}
	@Override
	public Store getStoreByID(int id) {		
		return storeDao.getStoreByID(id);
	}

}
