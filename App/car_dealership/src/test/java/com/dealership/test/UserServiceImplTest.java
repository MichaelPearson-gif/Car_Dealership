package com.dealership.test;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.dealership.dao.impl.UserDAOImpl;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.User;
import com.dealership.service.UserService;
import com.dealership.service.impl.UserServiceImpl;

class UserServiceImplTest {
	
	// Inject Mocks
	@InjectMocks
	private static UserService userService;
	
	// Mock the DAO implementation
	@Mock
	private UserDAOImpl userDAOImple;
	
	// Setup the userService to the implementation class
	// Make sure that this happens BeforeAll
	@BeforeAll
	public static void setup() {
		userService = new UserServiceImpl();
	}
	
	// Before each test, mock the DAO implementation
	@BeforeEach
	public void beforeSetup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateUser() {
		
		// Create a user to pass through the test
		User user = new User("MagicDoc", "goofyDoc", "Bill Smith", new Date(19580603));
	
		try {
			// Tell Mockito what to mock for this test
			Mockito.when(userDAOImple.createUser(user)).thenReturn(1);
			
			// Create an int value to store the createUser service method
			int c = userService.createUser(user);
			
			// Use assertEquals to test if it works
			Assertions.assertEquals(1, c);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Test if the method returns true when passwords match
	@Test
	void testLoginWhenTrue() {
		
		// Create username and password variables
		String username = "deathStar";
		String password = "cloneWars";
		
		try {
			
			// Tell Mockito what to mock for this test
			Mockito.when(userDAOImple.login(username)).thenReturn("cloneWars");
			
			// Create a boolean value to store the login service method
			boolean isTrue = userService.login(username, password);
			
			// Assert equals
			Assertions.assertEquals(true, isTrue);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// Test if the method returns false when passwords don't match
	@Test
	void testLoginWhenFalse() {
		
		// Create username and password variables
		String username = "deathStar";
		String password = "cloneWars";
		
		try {
			
			// Tell Mockito what to mock for this test
			Mockito.when(userDAOImple.login(username)).thenReturn("darkside4life");
			
			// Create a boolean value to store the login service method
			boolean isFalse = userService.login(username, password);
			
			// Assertion Equals
			Assertions.assertEquals(false, isFalse);
			
		}catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
