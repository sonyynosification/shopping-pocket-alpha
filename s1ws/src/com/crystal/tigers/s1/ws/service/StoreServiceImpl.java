package com.crystal.tigers.s1.ws.service;

import java.util.List;

import com.crystal.tigers.s1.ws.common.objects.SearchOrdering;
import com.crystal.tigers.s1.ws.common.utils.constants.SearchQualifiers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crystal.tigers.s1.ws.dao.StoreDao;
import com.crystal.tigers.s1.ws.model.Store;
@Service("storeService")
@Transactional
public class StoreServiceImpl implements IStoreService {

	@Autowired
	private StoreDao storeDao;

	@Override
	public List<Store> searchStore(Store store) {		
		return searchStore(store, SearchQualifiers.SEARCH_RANGE_UNLIMITED);
	}

    /**
     * Return the qualified store with maxReturn number of results
     * @param store
     * @param maxReturn number of results, or -1 for unlimited
     * @return
     */
	@Override
	public List<Store> searchStore(Store store, int maxReturn) {
	    SearchOrdering order = SearchOrdering.newInstance();
		return searchStore(store, maxReturn, order);
	}

    /**
     *
     * @param store
     * @param maxReturn
     * @param searchOrdering
     * @return
     */
	@Override
	public List<Store> searchStore(Store store, int maxReturn, SearchOrdering searchOrdering) {
		List<Store> results = storeDao.findStores(store, maxReturn, searchOrdering);
		return results;
	}

    @Override
    public List<Store> getAllStores() {
        Store store = new Store();
        return searchStore(store);

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

    /**
     * Check if a Store existed in storage or not
     * @param store
     * @return true if store did exist, or false if not
     */
	@Override
	public boolean exists(Store store) {
	    //TODO: we may need to have qualifiers for how stores "existed"
        int maxResults = 1;
		List<Store> foundStores = searchStore(store, maxResults);
		boolean isExisted = !foundStores.isEmpty();
		return isExisted;
	}

    /**
     * Save the store to persistent storage
     * @param newStore
     */
    @Override
    public void saveStore(Store newStore) {
        //TODO: implementation needed
		storeDao.createStore(newStore);
    }


    /**
     * Find a store by its ID
     * @param storeId
     * @return Store with appropriate ID, or null if not found
     */
	@Override
	public Store findById(int storeId) {
		Store foundStore = storeDao.getStoreByID(storeId);
		return foundStore;
	}

}
