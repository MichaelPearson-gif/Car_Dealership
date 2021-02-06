package com.dealership.main.menus;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.dealership.exceptions.BusinessException;
import com.dealership.model.Cars;
import com.dealership.service.CarsService;
import com.dealership.service.PaymentService;
import com.dealership.service.impl.CarsServiceImpl;
import com.dealership.service.impl.PaymentServiceImpl;

public class EmployeeMenu {

	// Constructor from super class
	public EmployeeMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Logger variable
	public static Logger log = Logger.getLogger(EmployeeMenu.class);
	
	// Scanner variable
	public static Scanner sc = new Scanner(System.in);
	
	// Instances of the service layers
	private static CarsService carsService = new CarsServiceImpl();
	private static PaymentService paymentService = new PaymentServiceImpl();
	
	// Instance of the offers menu
	private static OffersMenu offersMenu = new OffersMenu();
	
	// Switch case variable
	public static int ch = 0;
	
	// Method to call to open the employee menu
	public void employeeMenu(String username) throws BusinessException{
		
		// Loop through the choices
		do {
			
			// Employee menu options
			log.info("Welcome back " + username + ". What would you like to do?");
			log.info("");
			log.info("1) Add a new car to the lot");
			log.info("2) Review car offers");
			log.info("3) Remove a car from the lot");
			log.info("4) View Payments");
			log.info("5) Logout");
			
			// Parse through the switch case variable
			try {
				ch = Integer.parseInt(sc.nextLine());
			}catch (NumberFormatException e) {
				
			}
			
			// Switch Cases
			switch(ch) {
			case 1:
				// Create a Car object
				Cars car = new Cars();
				
				// Get car info
				log.info("Please fill in the car details.");
				log.info("");
				log.info("What is the car's make?");
				car.setMake(Integer.parseInt(sc.nextLine()));
				log.info("");
				log.info("What is the car's model?");
				car.setModel(sc.nextLine());
				log.info("");
				log.info("What color is the car?");
				car.setColor(sc.nextLine());
				log.info("");
				log.info("How much is the car?");
				car.setPrice(Double.parseDouble(sc.nextLine()));
				
				// Send info to the service layer
				carsService.newCar(car);
				
				break;
				
			case 2:
				offersMenu.offersMenu();
				break;
				
			case 3:
				
				// Variable to store employee input
				int carId;
				
				log.info("Please enter the car id for the car you are removing from the lot.");
				carId = Integer.parseInt(sc.nextLine());
				
				// Remove the car from the lot
				carsService.carUpdate(carId);

				break;
				
			case 4:
				// Initialize an int type variable to store employee input
				int paymentCarId;
				System.out.println("Please enter the car id you wish to view all payments for");
				paymentCarId = Integer.parseInt(sc.nextLine());
				
				// Send input to the payment service layer
				paymentService.allPayments(paymentCarId);
				
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
