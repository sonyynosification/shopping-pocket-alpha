package com.crystal.tigers.s1.s1ws.services.impl

import com.crystal.tigers.s1.s1ws.dbmodels.Store
import com.crystal.tigers.s1.s1ws.repositories.stores.StoreDAO
import com.crystal.tigers.s1.s1ws.services.StoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

/**
 * Created by sonyynoss on 8/16/18.
 */
@Service("newStoreSvc")
class StoreServiceImpl : StoreService {
    @Autowired
    lateinit var newStoreDao: StoreDAO

    override fun searchStore(store: Store): List<Store> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchStore(store: Store, maxReturn: Int): List<Store> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchStore(store: Store, maxReturn: Int, startingIndex: Int): List<Store> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createStore(store: Store): Boolean {
        return newStoreDao.save(store) != null
    }

    override fun findById(storeId: Int): Store? {
        return newStoreDao.findById(storeId).orElse(null)
    }

    override fun update(store: Store) {
        newStoreDao.save(store)
    }

    override fun deleteStore(store_id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStores(): List<Store> {
        return newStoreDao.findAll()
    }

    override fun getStores(maxReturn: Int): List<Store> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStoreByID(id: Int): Store {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun exists(store: Store): Boolean {
        val exampleStore : Example<Store> = Example.of(store)
        return newStoreDao.exists(exampleStore)
    }

    override fun saveStore(newStore: Store) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}