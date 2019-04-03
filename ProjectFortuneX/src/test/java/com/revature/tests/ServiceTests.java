package com.revature.tests;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.revature.bean.User;
import com.revature.services.UserService;

public class ServiceTests {
	UserService us = new UserService();
	
	@Test
	public void testMissingEmailForCreateUserService() {
		User newUser = new User(17, "", "testFirst", "testLast", "testPass");
		assertFalse(us.createUser(newUser));
	}

	@Test
	public void testMissingFirstNameForCreateUserService() {
		User newUser = new User(17, "testemail@gmail.com", "", "testLast", "testPass");
		assertFalse(us.createUser(newUser));
	}
	
	@Test
	public void testMissingLastNameForCreateUserService() {
		User newUser = new User(17, "testemail@gmail.com", "testFirst", "", "testPass");
		assertFalse(us.createUser(newUser));
	}
	
	@Test
	public void testMissingPasswordForCreateUserService() {
		User newUser = new User(17, "testemail@gmail.com", "testFirst", "testLast", "");
		assertFalse(us.createUser(newUser));
	}
}
