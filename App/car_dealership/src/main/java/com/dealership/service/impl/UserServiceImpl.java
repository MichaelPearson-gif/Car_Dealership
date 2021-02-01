package com.dealership.service.impl;

import org.apache.log4j.Logger;

import com.dealership.dao.UserDAO;
import com.dealership.dao.impl.UserDAOImpl;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.User;
import com.dealership.service.UserService;

public class UserServiceImpl implements UserService{

	// Create the connection between service layer and dao layer
	private UserDAO userDAO = new UserDAOImpl();
	
	// Import Logger
	Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Override
	public int createUser(User user) throws BusinessException {
		int c = 0;
		
		c = userDAO.createUser(user);
		
		return c;
	}

	@Override
	public boolean login(String username, String password) throws BusinessException {
		
		// Set default value to false
		boolean b = false;
		
		// Retrieve the corrsponding password from given username
		String storedPassword = userDAO.login(username);
		
		// Check to see if the stored password matches the user given one
		// If they are, change the boolean value as true
		if(password.equals(storedPassword)) {
			b = true;
		}
		
		return b;
	}

}
