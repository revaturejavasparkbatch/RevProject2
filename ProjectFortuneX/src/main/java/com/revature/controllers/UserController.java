package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.bean.User;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.exceptions.UserNotFoundException;
import com.revature.services.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class UserController {
	
	//replace all uses of UD
	UserDao ud = new UserDaoImpl();
	
	UserService userService = new UserService();
	
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
	
	//create user method (keep)
	@PostMapping(path="/apiusers", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User addUser(@RequestBody User user) {
		System.out.println(user);
		ud.createUser(user);
		List<User> userList = ud.getAllUsers();
		for(User u : userList) {
			System.out.println(u);
		}
		//change to homepage
		return user;
	}
	
	@PostMapping(path="/login", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> loginUser(@RequestBody User user, HttpServletRequest request) {
		System.out.println(user.getEmail());
		User confirmedUser = userService.confirmLogin(user);
		if(confirmedUser.getEmail().equals("null")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		HttpSession session = request.getSession();
		session.setAttribute("id", user.getId());
		session.setAttribute("email", user.getEmail());
		session.setAttribute("fName", user.getfName());
		session.setAttribute("lName", user.getlName());
		return new ResponseEntity<User>(confirmedUser, HttpStatus.OK);
	}
	
	//getting the session information
//	@GetMapping(path="/session", produces=MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public String getSessionInfo() {
//		
//	}
	
	
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
