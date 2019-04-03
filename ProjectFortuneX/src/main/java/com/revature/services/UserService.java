package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.revature.bean.User;
import com.revature.dao.UserDaoImpl;

@Service
@Component
public class UserService {

	UserDaoImpl userDaoImpl = new UserDaoImpl();
	
	//incorporate logic for pulling through all daoimpl methods
	public List<User> getAllUsers(){
		return userDaoImpl.getAllUsers();
	}
	
	public User getUserByEmail(String email) {
		return userDaoImpl.getUserByEmail(email);
	}
	
	public boolean createUser(User newUser) {
		//if any field is empty, return false
		if(newUser.getEmail().equals(""))
			return false;
		if(newUser.getfName().equals(""))
			return false;
		if(newUser.getlName().equals(""))
			return false;
		if(newUser.getPassword().equals(""))
			return false;
		
		return userDaoImpl.createUser(newUser);
	}
	
	public boolean updateUser(User change) {		
		userDaoImpl.updateUser(change);
		return true;
	}
	
	public void deleteUser(User user) {
		userDaoImpl.deleteUser(user);
	}
	
	public User confirmLogin(User user) {
		User checkUser = userDaoImpl.getUserByEmail(user.getEmail());
		if (checkUser != null && checkUser.getPassword().equals(user.getPassword())) {
				return checkUser;
			} else {
				user.setEmail("null");
				return user;
			}
		}
	}

