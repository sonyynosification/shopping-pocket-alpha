package com.crystal.tigers.s1.s1ws.repositories.Malls

import com.crystal.tigers.s1.s1ws.dbmodels.Mall

/**
 * Created by sonyynoss on 8/16/18.
 */
interface MallDAOCustom {

    fun find(Mall: Mall?): List<Mall>?
}