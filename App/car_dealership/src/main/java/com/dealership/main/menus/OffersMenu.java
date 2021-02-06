package com.dealership.main.menus;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.dealership.exceptions.BusinessException;
import com.dealership.service.OffersService;
import com.dealership.service.impl.OffersServiceImpl;

public class OffersMenu {

	// Constructor from super class
	public OffersMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Logger variable
	public static Logger log = Logger.getLogger(OffersMenu.class);

	// Scanner variable
	public static Scanner sc = new Scanner(System.in);

	// Instances of service layers
	private static OffersService offersService = new OffersServiceImpl();

	// Switch case variable
	public static int ch = 0;

	// Method to call to open the offers menu
	public void offersMenu() throws BusinessException {

		// Print out all pending car offers
		System.out.println("Printing all pending car offers...");
		System.out.println(offersService.allOffers());
		System.out.println("");

		// Loop through the options
		do {

			System.out.println("What would you like to do?");
			System.out.println("1) View all pending offers for a car");
			System.out.println("2) Approve or Decline offers");
			System.out.println("3) Return to the Employee Menu");

			// Parse through the switch case variable
			try {
				ch = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {

			}

			// Switch Cases
			switch (ch) {
			case 1:

				// Create variables to store user input data
				int carId;

				System.out.println("");
				System.out.println("Please enter the car id you wish to look for offers for.");
				carId = Integer.parseInt(sc.nextLine());
				System.out.println("Printing all offers for car id " + carId + "....");
				System.out.println("");
				System.out.println(offersService.carOffers(carId));
				break;

			case 2:

				// Create variables to store user input data
				int offerId;
				String status;

				System.out.println("");
				System.out.println("Please enter the offer id you wish to approve or decline");
				offerId = Integer.parseInt(sc.nextLine());
				System.out.println("");
				System.out.println("Please enter either Approved or Declined");
				status = sc.nextLine();

				// Send info to service layer
				offersService.statusUpdate(offerId, status);

				break;

			case 3:
				System.out.println("Returning to the Employee Menu");
				System.out.println("");
				break;

			default:
				System.out.println("Invalid menu option. Please select one of the mentioned options.");
				break;
			}

		} while (ch != 3);

	}

}
