package com.dealership.main.menus;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.dealership.exceptions.BusinessException;
import com.dealership.service.CarsService;
import com.dealership.service.UserService;
import com.dealership.service.impl.CarsServiceImpl;
import com.dealership.service.impl.UserServiceImpl;

public class CustomerMenu {

	// Constructor from super class
	public CustomerMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Logger variable
	public static Logger log = Logger.getLogger(EmployeeMenu.class);
	
	// Scanner variable
	public static Scanner sc = new Scanner(System.in);
	
	// Instances of the service layers
	private static UserService userService = new UserServiceImpl();
	private static CarsService carsService = new CarsServiceImpl();
	
	// Switch case variable
	public static int ch = 0;
	
	// Method to call to open customer menu
	public static void customerMenu(String username) throws BusinessException{
		
		// Loop through the choices
		do {
			
			// Customer menu options
			log.info("Welcome back " + username + ". What would you like to do?");
			log.info("");
			log.info("1) View cars on the lot");
			log.info("2) Make an offer on a car");
			log.info("3) View all your cars");
			log.info("4) View remaining payments on a car");
			log.info("5) Logout");
			
			// Parse through the switch case variable
			try {
				ch = Integer.parseInt(sc.nextLine());
			}catch (NumberFormatException e) {
				
			}
			
			// Switch Cases
			switch(ch) {
			case 1:
				log.info("This function is under construction.");
				break;
				
			case 2:
				log.info("This function is under construction.");
				break;
				
			case 3:
				log.info("This function is under construction.");
				break;
				
			case 4:
				log.info("This function is under construction.");
				break;
				
			case 5:
				log.info("Logging out....");
				break;
				
			default: log.info("Invalid menu option. Please retry selecting one of the mentioned options.");
				break;
				
			}
			
		}while(ch != 5);
		
	}
	
}
