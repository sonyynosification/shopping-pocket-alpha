package com.crystaltiger.controller;

import com.crystaltiger.model.User;
import com.crystaltiger.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// =========================================== Get All Users
	// ==========================================

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

	// =========================================== Create New User
	// ========================================

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		LOG.info("creating new user: {}", user);

		if (userService.exists(user)) {
			LOG.info("a user with name " + user.getUser_name() + " already exists");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{user_id}").buildAndExpand(user.getUser_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// =========================================== Get User By ID
	// =========================================

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
	
	// =========================================== Get User By ID
		// =========================================

		@RequestMapping(value = "search", method = RequestMethod.POST)
		public ResponseEntity<User> search(@RequestBody User user) {

			if (user == null) {
//				LOG.info("user with id {} not found", id);
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}

			List<User> usersFound = userService.searchByModel(user);
			
			return new ResponseEntity<User>(usersFound.get(0), HttpStatus.OK);
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

		currentUser.setUser_id(user.getUser_id());
		currentUser.setUser_name(user.getUser_name());
		currentUser.setUser_password(user.getUser_password());
		currentUser.setUser_location(user.getUser_location());
		currentUser.setUser_recent_search(user.getUser_recent_search());
		currentUser.setUser_favorite(user.getUser_favorite());
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
