package com.crystal.tigers.s1.s1ws.repositories.users

import com.crystal.tigers.s1.s1ws.dbmodels.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by sonyynoss on 8/16/18.
 */

@Repository("newUserDAO")
interface UserDAO : JpaRepository<User, Int>, UserDAOCustom {

}