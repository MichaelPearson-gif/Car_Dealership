package com.dealership.dao;

import com.dealership.model.User;

public interface UserDAO {

	// Creates a new user account
	public int createUser(User user);
	
	// User logs into their account
	public String login(String username);
}
