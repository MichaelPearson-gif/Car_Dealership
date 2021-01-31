package com.dealership.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.dealership.exceptions.BusinessException;
import com.dealership.service.UserService;
import com.dealership.service.impl.UserServiceImpl;

public class DealershipMain {

	// Logger variable
	private static Logger log = Logger.getLogger(DealershipMain.class);
	
	// Instance of the service layer
	private static UserService userService = new UserServiceImpl();
	
	private static RegisterMenu register;
	
	// Create a Scanner
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		// Switch case variable
		int ch = 0;
		
		// Loop through the switch case
		do {
			
			// Welcome messages with choices that the user can choose from
			log.info("Welcome to The Java Dealership where our coffee is hot and our prices are hotter!");
			log.info("----------------------------------------------------------");
			log.info("What would you like to do?");
			log.info("");
			log.info("1) Create a Java Dealership account");
			log.info("2) Login");
			log.info("3) Exit");
			
			// Parse the Switch case variable
			try {
				ch = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				
			}
			
			// Switch case menu options
			switch(ch) {
			
			case 1:
				try {
					register.Register();
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 2:
				log.info("This menu option is still under construction");
				break;
				
			case 3:
				log.info("Thank you for visiting The Java Dealership. We hope to see you again real soon.");
				break;
				
			default: log.info("Invalid menu option. Please enter an number from 1-3 to the corresponding menu option");
				break;
			
			}
			
		}while(ch != 3);

	}

}
