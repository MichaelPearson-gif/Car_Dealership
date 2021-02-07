package com.dealership.main.menus;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.dealership.exceptions.BusinessException;
import com.dealership.model.Cars;
import com.dealership.model.Offers;
import com.dealership.model.Payment;
import com.dealership.service.CarsService;
import com.dealership.service.OffersService;
import com.dealership.service.PaymentService;
import com.dealership.service.impl.CarsServiceImpl;
import com.dealership.service.impl.OffersServiceImpl;
import com.dealership.service.impl.PaymentServiceImpl;

public class CustomerMenu {

	// Constructor from super class
	public CustomerMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Logger variable
	public static Logger log = Logger.getLogger(CustomerMenu.class);
	
	// Scanner variable
	public static Scanner sc = new Scanner(System.in);
	
	// Instances of the service layers
	private static CarsService carsService = new CarsServiceImpl();
	private static OffersService offersService = new OffersServiceImpl();
	private static PaymentService paymentService = new PaymentServiceImpl();
	
	// Switch case variable
	public static int ch = 0;
	
	// Method to call to open customer menu
	public void customerMenu(String username) throws BusinessException{
		
		// Loop through the choices
		do {
			
			// Customer menu options
			System.out.println("Welcome back " + username + ". What would you like to do?");
			System.out.println("");
			System.out.println("1) View cars on the lot");
			System.out.println("2) Make an offer on a car");
			System.out.println("3) View all your cars");
			System.out.println("4) View remaining payments on a car");
			System.out.println("5) Make a Payment");
			System.out.println("6) Logout");
			
			// Parse through the switch case variable
			try {
				ch = Integer.parseInt(sc.nextLine());
			}catch (NumberFormatException e) {
				
			}
			
			// Switch Cases
			switch(ch) {
			case 1:
				System.out.println("Printing all cars on the lot....");
				System.out.println("");
				// Iterate through each car in the list and print it out
				for(Cars car : carsService.allCarsOnLot()) {
					System.out.println("\n" + car);
				}
				
				System.out.println("");
				break;
				
			case 2:
				// Create an offer object
				Offers offer = new Offers();
				System.out.println("Please enter the following ifo to make your offer");
				System.out.println("");
				offer.setUsername(username);
				System.out.println("Please enter the car id for the car you wish to put an offer on.");
				offer.setCarId(Integer.parseInt(sc.nextLine()));
				System.out.println("");
				System.out.println("How much is your offer?");
				offer.setOffer(Double.parseDouble(sc.nextLine()));
				System.out.println("");
				offersService.makeOffer(offer);
				break;
				
			case 3:
				System.out.println("Printing all your car details....");
				System.out.println("");
				System.out.println(carsService.customerCars(username));
				System.out.println("");
				break;
				
			case 4:
				// Initialize an int primitive type to store customers input
				int carId;
				System.out.println("Enter the car id you wish to view remaining payments for.");
				carId = Integer.parseInt(sc.nextLine());
				
				// Send to the Payment service layer
				paymentService.remainingPayment(carId);
				
				break;
				
			case 5:
				Payment payment = new Payment();
				System.out.println("Enter the car id to wish to make a payment to");
				payment.setCarId(Integer.parseInt(sc.nextLine()));
				System.out.println("");
				System.out.println("Please enter the amount you wish to pay");
				payment.setAmount(Double.parseDouble(sc.nextLine()));
				
				// Send info to the payment Service layer
				paymentService.makePayment(payment);
				
				break;
				
			case 6:
				System.out.println("Logging out.........");
				break;
				
			default: System.out.println("Invalid menu option. Please retry selecting one of the mentioned options.");
				break;
				
			}
			
		}while(ch != 6);
		
	}
	
}
