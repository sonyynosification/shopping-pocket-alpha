package com.crystal.tigers.s1.s1ws.services

import com.crystal.tigers.s1.s1ws.dbmodels.Mall

/**
 * Created by sonyynoss on 8/16/18.
 */
interface MallService {

    fun create(mall: Mall): Boolean

    fun exists(mall: Mall): Boolean = false
    fun update(mall: Mall): Boolean = false
    fun delete(id: Int) {}
    fun findById(id: Int): Mall?
    fun findAllMalls(): List<Mall>
    fun searchByModel(mall: Mall?): List<Mall>
}