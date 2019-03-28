package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.bean.User;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.exceptions.UserNotFoundException;

@Controller
public class UserController {
	
	UserDao ud = new UserDaoImpl();
	
	@GetMapping("/apiusers")
	@ResponseBody
	public List<User> getUsers(@RequestParam(value="email", required=false)String email){
		if(email != null) {
			User u = ud.getUserByEmail(email);
			if(u == null) 
				throw new UserNotFoundException();
			
			else {
				ArrayList<User> user = new ArrayList<>();
				user.add(u);
				return user;
			}
		}
		return ud.getAllUsers();
	}
}
