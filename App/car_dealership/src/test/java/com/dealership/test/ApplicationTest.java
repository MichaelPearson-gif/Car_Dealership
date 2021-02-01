package com.dealership.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.dealership.dao.UserDAO;
import com.dealership.dao.impl.UserDAOImpl;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.User;

public class ApplicationTest {

	// Create an instance of the layers and models
	private static User user;
	private static UserDAO userDAO;
	
	// Setting up the test on the UserDAO layer
	@BeforeAll
	public static void setUpUserDAO() {
		
		userDAO = new UserDAOImpl();
		
	}
	
	// Test the connections of the DAO layer
	@Test
	public void testCreateUser() {
		
		// Make a new user
		user.setUsername("employee1");
		user.setPasswords("password");
		user.setUsersname("John Doe");
		user.setAge(21);
		user.setDob(new Date());
		
		try {
			int output = userDAO.createUser(user);
			assertEquals(output, 1);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
