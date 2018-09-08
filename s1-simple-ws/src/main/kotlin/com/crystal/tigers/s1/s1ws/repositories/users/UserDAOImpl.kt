package com.crystal.tigers.s1.s1ws.repositories.users

import com.crystal.tigers.s1.s1ws.dbmodels.User
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


/**
 * Created by sonyynoss on 8/16/18.
 */

@Repository
class UserDAOImpl : UserDAOCustom {
    @PersistenceContext lateinit var entityManager: EntityManager

    override fun find(user: User?): List<User>? {

        val query = entityManager.createNativeQuery("SELECT * FROM user where user_name LIKE ?", User::class.java)
        query.setParameter(1, user?.userName + "%")
        var retLst: MutableList<User> = mutableListOf()
        query.getResultList().forEach { x -> retLst.add(x as User) }
        return retLst.toList()
    }

}