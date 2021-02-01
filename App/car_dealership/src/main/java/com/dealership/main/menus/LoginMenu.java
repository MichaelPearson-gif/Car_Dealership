package com.dealership.main.menus;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class LoginMenu {

	// Logger variable
	public static Logger log = Logger.getLogger(LoginMenu.class);
	
	// Scanner variable
	public static Scanner sc = new Scanner(System.in);
	
	// Switch case variable
	int ch = 0;
	
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
		
		}
		
	}while(ch != 0);

	// Constructor from super class
	public LoginMenu() {
		super();
		// TODO Auto-generated constructor stub
	}
}
