package com.crystal.tigers.s1.s1ws.repositories.stores

import com.crystal.tigers.s1.s1ws.dbmodels.Store
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by sonyynoss on 8/16/18.
 */
@Repository("newStoreDao")
interface StoreDAO : JpaRepository<Store, Int> {

}