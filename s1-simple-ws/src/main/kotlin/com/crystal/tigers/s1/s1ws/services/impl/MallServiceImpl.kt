package com.crystal.tigers.s1.s1ws.services.impl

import com.crystal.tigers.s1.s1ws.dbmodels.Mall
import com.crystal.tigers.s1.s1ws.repositories.Malls.MallDAO
import com.crystal.tigers.s1.s1ws.services.MallService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by sonyynoss on 8/16/18.
 */

@Service("newMallSvc")
class MallServiceImpl : MallService {
    @Autowired lateinit var newMallDAO: MallDAO

    override fun findById(id: Int): Mall? {
        return newMallDAO.findById(id).orElse(null);
    }

    override fun findAllMalls(): List<Mall> {
        return newMallDAO.findAll()
    }

    override fun searchByModel(mall: Mall?): List<Mall> {

        val malls: List<Mall>? = newMallDAO.find(mall)
        return malls ?: listOf()
    }

    override fun create(mall: Mall): Boolean {
        return newMallDAO.save(mall) != null
    }

    override fun update(mall: Mall): Boolean {
        return newMallDAO.save(mall) != null
    }

}