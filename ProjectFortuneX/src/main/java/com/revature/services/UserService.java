package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bean.User;
import com.revature.dao.UserDaoImpl;

@Service
public class UserService {

	UserDaoImpl userDaoImpl = new UserDaoImpl();
	
	//incorporate logic for pulling through all daoimpl methods
	
	
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

