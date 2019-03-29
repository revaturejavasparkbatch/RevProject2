package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	//create user page (testing)
	@GetMapping("/new")
	public String returnNewUserPage() {
		return "NewUser";
	}
	
	//create user method (keep)
	@PostMapping("/apiusers")
	public String addUser(HttpServletRequest request) {
		String first = request.getParameter("first");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String last = request.getParameter("last");
		ud.createUser(new User(email, first, last, pass));
		return "redirect:/apiusers";
	}
	
	//update user page (testing)
	@GetMapping("/update")
	public String returnUpdateUserPage() {
		return "UpdateUser";
	}
	
	//update user method (keep)
	@PostMapping("/update")
	public String updateUser(@RequestParam("id")Integer id, @RequestParam("email")String email, @RequestParam("first")String first, @RequestParam("last")String last, @RequestParam("pass")String pass) {
		User change = new User(id, email, first, last, pass);
		ud.updateUser(change);
		return "redirect:/apiusers";
	}
	
	//delete user page
	@GetMapping("/delete")
	public String rturnDeleteUserPage() {
		return "DeleteUser";
	}
	
	//delete user method (keep)
	@PostMapping("/delete")
	public String deleteUser(@RequestParam("id")Integer id, @RequestParam("email")String email, @RequestParam("first")String first, @RequestParam("last")String last, @RequestParam("pass")String pass) {
		User delete = new User(id, email, first, last, pass);
		ud.deleteUser(delete);
		return "redirect:/apiusers";
	}
}
