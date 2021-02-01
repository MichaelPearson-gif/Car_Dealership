package com.dealership.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.Chronology;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Date;
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
	public static void Register() throws BusinessException{
		
		// New user object to populate the db with the new customer details
		User user = new User();
		
		// Create a SimpleDateFormat to convert a string to a date format
		// Set the leniency to false
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		
		try {
			// Getting User Info
			log.info("Please fill out the info below");
			log.info("");
			log.info("Username");
			user.setUsername(sc.nextLine());
			log.info("");
			log.info("Password");
			user.setPasswords(sc.nextLine());
			log.info("");
			log.info("Please enter your full name");
			user.setUsersname(sc.nextLine());
			log.info("");
			log.info("How old are you?");
			user.setAge(Integer.parseInt(sc.nextLine()));
			log.info("");
			log.info("Date of birth (yyyy-mm-dd)");
			user.setDob(sdf.parse(sc.nextLine()));
			
			userService.createUser(user);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
