package com.revature.dao;

import java.util.List;

import com.revature.bean.User;

public interface UserDao {
	public List<User> getAllUsers();
	public User getUserByEmail(String email);
	public boolean createUser(User newUser);
	public void updateUser(User change);
	public boolean deleteUser(User user);
}
