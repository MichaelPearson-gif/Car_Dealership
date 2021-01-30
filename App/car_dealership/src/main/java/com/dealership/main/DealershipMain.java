package com.dealership.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.dealership.service.UserService;
import com.dealership.service.impl.UserServiceImpl;

public class DealershipMain {

	// Logger variable
	private static Logger log = Logger.getLogger(DealershipMain.class);
	
	// Instance of the service layer
	private static UserService userService = new UserServiceImpl();
	
	// Create a Scanner
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		

	}

}
