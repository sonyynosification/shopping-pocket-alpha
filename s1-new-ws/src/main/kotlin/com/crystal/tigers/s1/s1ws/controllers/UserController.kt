package com.crystal.tigers.s1.s1ws.controllers

import com.crystal.tigers.s1.s1ws.dbmodels.User
import com.crystal.tigers.s1.s1ws.services.IUserService
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.io.IOException
import java.util.HashMap

@RestController
@RequestMapping(value = "/users")
class UserController(@Autowired private val userService: IUserService) {

    private val logger: Logger = LoggerFactory.getLogger(UserController::class.java)

    // =========================================== Get All Users ==========================================

    @RequestMapping(value = ["/list"], method = [RequestMethod.GET])
    fun getAll(): ResponseEntity<List<User>> {
        logger.info("getting all users")
        val users: MutableList<User>? = userService.findAllUsers()
        if (users == null || users.isEmpty()) {
            logger.info("no users found")
            return ResponseEntity(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity(users, HttpStatus.OK)
    }

    // =========================================== Create New com.crystal.tigers.s1.s1ws.dbmodels.User ========================================

    @RequestMapping(value = ["/register"], method = [(RequestMethod.POST)])
    fun create(@RequestBody user: User, ucBuilder: UriComponentsBuilder): ResponseEntity<Void> {
        logger.info("creating new user: {}", user)

        if (userService.exists(user)) {
            logger.info("a user with name " + user.userName + " already exists")
            return ResponseEntity(HttpStatus.CONFLICT)
        }

        userService.saveUser(user)

        val headers = HttpHeaders()
        headers.location = ucBuilder.path("/user/{user_id}").buildAndExpand(user.userId).toUri()
        return ResponseEntity(headers, HttpStatus.CREATED)
    }

    // =========================================== Get com.crystal.tigers.s1.s1ws.dbmodels.User By ID =========================================

    @RequestMapping(value = ["{user_id}"], method = [(RequestMethod.GET)])
    operator fun get(@PathVariable("user_id") id: Int): ResponseEntity<User> {
        logger.info("getting user with id: {}", id)
        val user = userService.findById(id)

        if (user == null) {
            logger.info("user with id {} not found", id)
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        return ResponseEntity(user, HttpStatus.OK)
    }

    // =========================================== Search Users =========================================

    @RequestMapping(value = ["search"], method = [(RequestMethod.POST)])
    @Throws(JsonParseException::class, JsonMappingException::class, IOException::class)
    fun search(@RequestBody jsonStr: String): ResponseEntity<Map<String, List<User>>> {
        val mapperObj = ObjectMapper()

        val user = mapperObj.readValue(jsonStr, User::class.java)
        println("jsonStr$jsonStr")

        val results = HashMap<String, List<User>>()

        if (user == null || "" == jsonStr) {
            return ResponseEntity(results, HttpStatus.NOT_FOUND)
        }

        val userList = userService.searchByModel(user)

        results.put("users", userList)
        logger.info("results: ", results)
        return ResponseEntity(results, HttpStatus.OK)
        //return new ResponseEntity<Map<String, List<com.crystal.tigers.s1.s1ws.dbmodels.User>>>(results, HttpStatus.OK);
    }

    // =========================================== Update Existing com.crystal.tigers.s1.s1ws.dbmodels.User
    // ===================================

    @RequestMapping(value = ["/update/{user_id}"], method = [(RequestMethod.PUT)])
    fun update(@PathVariable user_id: Int, @RequestBody user: User): ResponseEntity<User> {
        logger.info("updating user: {}", user)
        val currentUser = userService.findById(user_id)

        if (currentUser == null) {
            logger.info("com.crystal.tigers.s1.s1ws.dbmodels.User with id {} not found", user_id)
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        currentUser.userId = user.userId
        currentUser.userName = user.userName
        currentUser.userPassword = user.userPassword
        currentUser.userLocation = user.userLocation
        currentUser.userRecentSearch = user.userRecentSearch
        currentUser.userFavorite = user.userFavorite
        userService.updateUser(user)
        return ResponseEntity(currentUser, HttpStatus.OK)
    }

    // =========================================== Delete com.crystal.tigers.s1.s1ws.dbmodels.User
    // ============================================

    @RequestMapping(value = ["delete/{user_id}"], method = [(RequestMethod.DELETE)])
    fun delete(@PathVariable("user_id") id: Int): ResponseEntity<Void> {
        logger.info("deleting user with id: {}", id)
        val user = userService.findById(id)

        if (user == null) {
            logger.info("Unable to delete. com.crystal.tigers.s1.s1ws.dbmodels.User with id {} not found", id)
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        userService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }

}