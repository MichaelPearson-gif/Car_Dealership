package com.dealership.main.menus;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.dealership.exceptions.BusinessException;
import com.dealership.service.UserService;
import com.dealership.service.impl.UserServiceImpl;

public class LoginMenu {
	
	// Constructor from super class
	public LoginMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Logger variable
	public static Logger log = Logger.getLogger(LoginMenu.class);
	
	// Scanner variable
	public static Scanner sc = new Scanner(System.in);
	
	// Instance of the service layer
	private static UserService userService = new UserServiceImpl();
	
	// Employee and Customer menus
	private static EmployeeMenu employee = new EmployeeMenu();
	
	// Switch case variable
	public static int ch = 0;
	
	public static void login() throws BusinessException {
		// Loop through user choices
		do {
			
			// User choices
			log.info("Please select one of the following choices");
			log.info("");
			log.info("1) Input login credentials");
			log.info("2) Go back to the Main Menu");
			
			// Parse through the switch case variable
			try {
				ch = Integer.parseInt(sc.nextLine());
			}catch (NumberFormatException e) {
				
			}
			
			// Switch cases
			switch(ch) {
			case 1:
				// Get the user inputs
				log.info("Please enter you username");
				String username = sc.nextLine();
				log.info("");
				log.info("Please enter in your password");
				String password = sc.nextLine();
				
				// Check to see if the username is an employee or customer
				if(username.equals("employee1")) {
					
					// Verify the password
					if(userService.login(username, password) == true) {
						// Send them to the employee menu
						employee.employeeMenu(username);
					}else {
						log.info("The password you entered was incorrect. Please try again.");
					}
					
				}else {
					
					// Verify the password for a customer login
					if(userService.login(username, password) == true) {
						// Send them to the customer menu
						log.info("This menu is still under construction");
					}else {
						log.info("The password you entered was incorrect. Please try again.");
					}
					
				}
				break;
				
			case 2:
				log.info("Going backto the Main Menu");
				break;
				
			default: log.info("Invalid menu option. Please retry selecting one of the mentioned options.");
				break;
			}
			
		}while(ch != 2);
	
	}

}
