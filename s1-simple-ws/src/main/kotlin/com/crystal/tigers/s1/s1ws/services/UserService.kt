package com.crystal.tigers.s1.s1ws.services

import com.crystal.tigers.s1.s1ws.dbmodels.User

/**
 * Created by sonyynoss on 8/16/18.
 */
interface UserService {

    fun create(user: User): Boolean

    fun exists(user: User): Boolean = false
    fun updateUser(user: User) {}
    fun delete(id: Int) {}
    fun findById(id: Int): User
    fun findAllUsers(): List<User>
    fun searchByModel(user: User?): List<User>
}