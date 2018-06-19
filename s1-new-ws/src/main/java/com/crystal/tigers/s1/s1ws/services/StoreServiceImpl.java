package com.crystal.tigers.s1.s1ws.services;

import java.util.List;

import com.crystal.tigers.s1.s1ws.dao.StoreDao;
import com.crystal.tigers.s1.s1ws.dbmodels.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("storeService")
@Transactional
public class StoreServiceImpl implements IStoreService {

	@Autowired
	private StoreDao storeDao;

	@Override
	public List<Store> searchStore(Store store) {
		return storeDao.searchStore(store);
	}

	@Override
	public List<Store> searchStore(Store store, int maxReturn) {
	    //TODO: implementation needed
		return null;
	}

	@Override
	public List<Store> searchStore(Store store, int maxReturn, int startingIndex) {
		//TODO: implementation needed
		return null;
	}

	@Override
	public List<Store> getStores() {
		// TODO Auto-generated method stub
		return storeDao.getStores();
	}

	@Override
	public List<Store> getStores(int maxReturn) {
        //TODO: implementation needed
		return null;
	}

	@Override
	public void createStore(Store store) {
		storeDao.createStore(store);
		
	}

	@Override
	public void updateStore(Store store) {
        //TODO: implementation needed
		
	}

	@Override
	public void deleteStore(int store_id) {
		storeDao.deleteStore(store_id);
		
	}
	@Override
	public Store getStoreByID(int id) {		
		return storeDao.getStoreByID(id);
	}

	@Override
	public boolean exists(Store store) {
		//TODO: implementation needed
		return false;
	}

    @Override
    public void saveStore(Store newStore) {
        //TODO: implementation needed
    }

}
