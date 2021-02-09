package com.dealership.main;


import java.util.Scanner;
import com.dealership.exceptions.BusinessException;
import com.dealership.main.menus.LoginMenu;
import com.dealership.main.menus.RegisterMenu;

public class DealershipMain {
	
	private static RegisterMenu register = new RegisterMenu();
	private static LoginMenu login = new LoginMenu();
	
	// Create a Scanner
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		// Switch case variable
		int ch = 0;
		
		// Loop through the switch case
		do {
			
			// Welcome messages with choices that the user can choose from
			System.out.println("Welcome to The Java Dealership where our coffee is hot and our prices are hotter!");
			System.out.println("----------------------------------------------------------");
			System.out.println("What would you like to do?");
			System.out.println("");
			System.out.println("1) Create a Java Dealership account");
			System.out.println("2) Login");
			System.out.println("3) Exit");
			
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
				
				try {
					login.login();
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 3:
				System.out.println("Thank you for visiting The Java Dealership. We hope to see you again real soon.");
				break;
				
			default: System.out.println("Invalid menu option. Please enter an number from 1-3 to the corresponding menu option");
				break;
			
			}
			
		}while(ch != 3);

	}

}
