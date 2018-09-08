package com.crystal.tigers.s1.s1ws.repositories.Malls

import com.crystal.tigers.s1.s1ws.dbmodels.Mall
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


/**
 * Created by sonyynoss on 8/16/18.
 */

@Repository
class MallDAOImpl : MallDAOCustom {
    @PersistenceContext lateinit var entityManager: EntityManager

    override fun find(mall: Mall?): List<Mall>? {

        val query = entityManager.createNativeQuery("SELECT * FROM mall where mall_name LIKE ?", Mall::class.java)
        query.setParameter(1, mall?.mallName + "%")
        var retLst: MutableList<Mall> = mutableListOf()
        query.getResultList().forEach { x -> retLst.add(x as Mall) }
        return retLst.toList()
    }

}