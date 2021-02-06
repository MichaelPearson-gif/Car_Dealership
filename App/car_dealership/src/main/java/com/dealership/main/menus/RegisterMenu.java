package com.dealership.main.menus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.dealership.exceptions.BusinessException;
import com.dealership.model.User;
import com.dealership.service.UserService;
import com.dealership.service.impl.UserServiceImpl;

public class RegisterMenu {

	// Logger variable
	public static Logger log = Logger.getLogger(RegisterMenu.class);
	
	// Scanner
	public static Scanner sc = new Scanner(System.in);

	// Constructor from super class
	public RegisterMenu() {
		super();
		
	}
	
	// Instance of the service layer
	private static UserService userService = new UserServiceImpl();
	
	// Method that will hold the register menu.
	public void Register() throws BusinessException{
		
		// New user object to populate the db with the new customer details
		User user = new User();
		
		// Create a SimpleDateFormat to convert a string to a date format
		// Set the leniency to false
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		
		try {
			// Getting User Info
			System.out.println("Please fill out the info below");
			System.out.println("");
			System.out.println("Username");
			user.setUsername(sc.nextLine());
			System.out.println("");
			System.out.println("Password");
			user.setPasswords(sc.nextLine());
			System.out.println("");
			System.out.println("Please enter your full name");
			user.setUsersname(sc.nextLine());
			System.out.println("");
			System.out.println("Date of birth (yyyy-mm-dd)");
			user.setDob(sdf.parse(sc.nextLine()));
			
			userService.createUser(user);
			
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		
		
	}
	
	
}
