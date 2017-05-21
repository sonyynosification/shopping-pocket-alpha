package com.crystal.tigers.s1.ws.controller;

import com.crystal.tigers.s1.ws.model.User;
import com.crystal.tigers.s1.ws.service.IUserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	// =========================================== Get All Users ==========================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAll() {
		LOG.info("getting all users");
		List<User> users = userService.findAllUsers();
		if (users == null || users.isEmpty()) {
			LOG.info("no users found");
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// =========================================== Create New User ========================================

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		LOG.info("creating new user: {}", user);

		if (userService.exists(user)) {
			LOG.info("a user with name " + user.getUserName() + " already exists");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{user_id}").buildAndExpand(user.getUserId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// =========================================== Get User By ID =========================================

	@RequestMapping(value = "{user_id}", method = RequestMethod.GET)
	public ResponseEntity<User> get(@PathVariable("user_id") int id) {
		LOG.info("getting user with id: {}", id);
		User user = userService.findById(id);

		if (user == null) {
			LOG.info("user with id {} not found", id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	// =========================================== Search Users =========================================

	@RequestMapping(value = "search", method = RequestMethod.POST)
	public ResponseEntity<Map<String, List<User>>> search(@RequestBody String jsonStr) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapperObj = new ObjectMapper();
		
		User user = mapperObj.readValue(jsonStr, User.class);
		System.out.println("jsonStr" + jsonStr);
					
		if (user == null || "".equals(jsonStr)) {			
			return new ResponseEntity("no user found", HttpStatus.NOT_FOUND);
		}

		List<User> userList = userService.searchByModel(user);
		Map results = new HashMap();
		results.put("users", userList);
		LOG.info("results: ", results);
		return new ResponseEntity(results, HttpStatus.OK);
		//return new ResponseEntity<Map<String, List<User>>>(results, HttpStatus.OK);
	}

	// =========================================== Update Existing User
	// ===================================

	@RequestMapping(value = "/update/{user_id}", method = RequestMethod.PUT)
	public ResponseEntity<User> update(@PathVariable int user_id, @RequestBody User user) {
		LOG.info("updating user: {}", user);
		User currentUser = userService.findById(user_id);

		if (currentUser == null) {
			LOG.info("User with id {} not found", user_id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser.setUserId(user.getUserId());
		currentUser.setUserName(user.getUserName());
		currentUser.setUserPassword(user.getUserPassword());
		currentUser.setUserLocation(user.getUserLocation());
		currentUser.setUserRecentSearch(user.getUserRecentSearch());
		currentUser.setUserFavorite(user.getUserFavorite());
		userService.updateUser(user);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// =========================================== Delete User
	// ============================================

	@RequestMapping(value = "delete/{user_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("user_id") int id){
        LOG.info("deleting user with id: {}", id);
        User user = userService.findById(id);

        if (user == null){
            LOG.info("Unable to delete. User with id {} not found", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        userService.delete(id);  
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
