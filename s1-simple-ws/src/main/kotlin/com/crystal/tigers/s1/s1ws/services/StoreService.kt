package com.crystal.tigers.s1.s1ws.services

import com.crystal.tigers.s1.s1ws.dbmodels.Store

/**
 * Created by sonyynoss on 8/16/18.
 */
interface StoreService {
    abstract fun searchStore(store: Store): List<Store>

    abstract fun searchStore(store: Store, maxReturn: Int): List<Store>

    abstract fun searchStore(store: Store, maxReturn: Int, startingIndex: Int): List<Store>

    //TODO: should we handle the return value? If yes, a meaningful error message can be provided to user
    abstract fun createStore(store: Store): Boolean

    //TODO: should we handle the return value? If yes, a meaningful error message can be provided to user
    abstract fun update(store: Store)

    //TODO: should we handle the return value? If yes, a meaningful error message can be provided to user
    abstract fun deleteStore(store_id: Int)

    fun getStores(): List<Store>

    fun getStores(maxReturn: Int): List<Store>

    fun getStoreByID(id: Int): Store

    fun exists(store: Store): Boolean

    fun saveStore(newStore: Store)

    fun findById(storeId: Int): Store?
}