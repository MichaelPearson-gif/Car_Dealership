package com.dealership.service;

import com.dealership.exceptions.BusinessException;
import com.dealership.model.User;

public interface UserService {

	// Create a new user
	public int createUser(User user) throws BusinessException;
	
	// User can log into their account
	public boolean login(String username, String password) throws BusinessException;
	
}
