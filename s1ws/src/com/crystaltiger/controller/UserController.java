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

	 /* Getting List of objects in Json format in Spring Restful Services */
	 @RequestMapping(value = "/list", method = RequestMethod.GET)
	 public @ResponseBody
	 List getEmployee() {

	  List userList = null;
	  try {
		  userList = userService.findAllUsers();

	  } catch (Exception e) {
	   e.printStackTrace();
	  }

	  return userList;
	 }

	// =========================================== Get User By ID
	// =========================================

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<User> get(@PathVariable("id") int id) {
		LOG.info("getting user with id: {}", id);
		User user = userService.findById(id);

		if (user == null) {
			LOG.info("user with id {} not found", id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// =========================================== Create New User
	// ========================================

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		LOG.info("creating new user: {}", user);

		if (userService.exists(user)) {
			LOG.info("a user with name " + user.getUserName() + " already exists");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{user_id}").buildAndExpand(user.getUser_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// =========================================== Update Existing User
	// ===================================

	/*
	 * @RequestMapping(value = "{id}", method = RequestMethod.PUT) public
	 * ResponseEntity<User> update(@PathVariable int id, @RequestBody User
	 * user){ LOG.info("updating user: {}", user); User currentUser =
	 * userService.findById(id);
	 * 
	 * if (currentUser == null){ LOG.info("User with id {} not found", id);
	 * return new ResponseEntity<User>(HttpStatus.NOT_FOUND); }
	 * 
	 * currentUser.setId(user.getId());
	 * currentUser.setUsername(user.getUsername());
	 * 
	 * userService.update(user); return new ResponseEntity<User>(currentUser,
	 * HttpStatus.OK); }
	 */

	// =========================================== Delete User
	// ============================================

	/*
	 * @RequestMapping(value = "{}", method = RequestMethod.DELETE) public
	 * ResponseEntity<Void> delete(@PathVariable("id") int id){
	 * LOG.info("deleting user with id: {}", id); User user =
	 * userService.findById(id);
	 * 
	 * if (user == null){
	 * LOG.info("Unable to delete. User with id {} not found", id); return new
	 * ResponseEntity<Void>(HttpStatus.NOT_FOUND); }
	 * 
	 * userService.deleteUserByName(userName);(id); return new
	 * ResponseEntity<Void>(HttpStatus.OK); }
	 */
}
