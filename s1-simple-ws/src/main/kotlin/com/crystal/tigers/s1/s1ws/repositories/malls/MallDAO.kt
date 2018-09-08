package com.crystal.tigers.s1.s1ws.repositories.Malls

import com.crystal.tigers.s1.s1ws.dbmodels.Mall
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by sonyynoss on 8/16/18.
 */

@Repository("newMallDAO")
interface MallDAO : JpaRepository<Mall, Int>, MallDAOCustom {

}