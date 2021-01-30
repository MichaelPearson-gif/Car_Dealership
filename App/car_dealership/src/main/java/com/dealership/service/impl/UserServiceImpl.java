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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean login(String username, String password) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

}
