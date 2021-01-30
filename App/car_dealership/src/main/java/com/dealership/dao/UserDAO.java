package com.dealership.dao;

import com.dealership.exceptions.BusinessException;
import com.dealership.model.User;

public interface UserDAO {

	// Creates a new user account
	public int createUser(User user) throws BusinessException;
	
	// User logs into their account
	public String login(String username);
}
