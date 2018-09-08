package com.crystal.tigers.s1.s1ws.repositories.users

import com.crystal.tigers.s1.s1ws.dbmodels.User

/**
 * Created by sonyynoss on 8/16/18.
 */
interface UserDAOCustom {

    fun find(user: User?): List<User>?
}