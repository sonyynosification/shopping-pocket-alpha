package com.crystal.tigers.s1.s1ws.services.impl

import com.crystal.tigers.s1.s1ws.dbmodels.User
import com.crystal.tigers.s1.s1ws.repositories.users.UserDAO
import com.crystal.tigers.s1.s1ws.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by sonyynoss on 8/16/18.
 */

@Service("newUserSvc")
class UserServiceImpl : UserService {
    @Autowired lateinit var newUserDAO: UserDAO

    override fun findById(id: Int): User {
        return newUserDAO.findById(id).get();
    }

    override fun findAllUsers(): List<User> {
        return newUserDAO.findAll()
    }

    override fun searchByModel(user: User?): List<User> {

        val users:List<User>? =  newUserDAO.find(user)
        if (users != null) {
            return users;
        }
        return listOf()
    }

    override fun create(user: User): Boolean {
        return newUserDAO.save(user) != null
    }

}