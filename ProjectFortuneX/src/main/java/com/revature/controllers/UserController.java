package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.bean.User;
import com.revature.exceptions.UserNotFoundException;
import com.revature.services.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@Component
public class UserController {
	
	//@Autowired
	UserService userService = new UserService();
	
	//global obj to persist user state across pages and page refreshes (keep for backup) 
	//User currUser = new User();
	
	@GetMapping(path="/apiusers", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<User>> getUsers(@RequestParam(value="email", required=false)String email){
		if(email != null) {
			User u = userService.getUserByEmail(email);
			if(u == null) 
				throw new UserNotFoundException();
			
			else {
				List<User> user = new ArrayList<>();
				user.add(u);
				return new ResponseEntity<List<User>>(user, HttpStatus.OK);
			}
		}
		List<User> allUsers = userService.getAllUsers();
		return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
	}
	
	//create user method (keep)
	@PostMapping(path="/apiusers", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> addUser(@RequestBody User user) {
		boolean created = userService.createUser(user);
		if(created)
			return new ResponseEntity<User>(user, HttpStatus.OK);
		
		//empty user obj to show error through response body
		User empUser = new User("", "", "", "");
		return new ResponseEntity<User>(empUser, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	//update user method (return updated object and status code updated)
	@PostMapping(path="/update", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User change) {
		boolean updated = userService.updateUser(change);
		if(updated)
			return new ResponseEntity<User>(change, HttpStatus.OK);
		
		//empty user obj to show error through response body
		User empUser = new User("", "", "", "");
		return new ResponseEntity<User>(empUser, HttpStatus.UNPROCESSABLE_ENTITY);
	}
		
	//delete user method (return status code deleted)
	@PostMapping(path="/delete", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteUser(@RequestBody User delete) {
		userService.deleteUser(delete);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	//following methods creates endpoints to persist, edit, and receive user data (keep as back up)
//	@PostMapping(path="/login", consumes=MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseEntity<User> loginUser(@RequestBody User user) {
//		User confirmedUser = userService.confirmLogin(user);
//		if(confirmedUser.getEmail().equals("null")) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		currUser = confirmedUser;
//		return new ResponseEntity<User>(confirmedUser, HttpStatus.OK);
//	}
//	
//	//getting the session information
//	@GetMapping(path="/session", produces=MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseEntity<User> sessionInfo() {
//		if(!currUser.getEmail().equals("")) {
//			return new ResponseEntity<User>(currUser, HttpStatus.OK);
//		}
//		
//		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//	}
//	
//	@PostMapping(path="/session", consumes=MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseEntity<User> invalidateSession() {		
//		if(!currUser.getEmail().equals("")) {
//			currUser.setId(null);
//			currUser.setEmail("");
//			currUser.setfName("");
//			currUser.setlName("");
//			currUser.setPassword("");
//			return new ResponseEntity<User>(currUser, HttpStatus.OK);
//		}
//		
//		return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
//	}
}