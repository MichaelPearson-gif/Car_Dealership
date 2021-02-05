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
		log.info("Printing all pending car offers...");
		log.info(offersService.allOffers());
		log.info("");

		// Loop through the options
		do {

			log.info("What would you like to do?");
			log.info("1) View all pending offers for a car");
			log.info("2) Approve or Decline offers");
			log.info("3) Return to the Employee Menu");

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

				log.info("");
				log.info("Please enter the car id you wish to look for offers for.");
				carId = Integer.parseInt(sc.nextLine());
				log.info("Printing all offers for car id " + carId + "....");
				log.info("");
				log.info(offersService.carOffers(carId));
				break;

			case 2:

				// Create variables to store user input data
				int offerId;
				String status;

				log.info("");
				log.info("Please enter the offer id you wish to approve or decline");
				offerId = Integer.parseInt(sc.nextLine());
				log.info("");
				log.info("Please enter either Approved or Declined");
				status = sc.nextLine();

				// Send info to service layer
				offersService.statusUpdate(offerId, status);

				break;

			case 3:
				log.info("Returning to the Employee Menu");
				log.info("");
				break;

			default:
				log.info("Invalid menu option. Please select one of the mentioned options.");
				break;
			}

		} while (ch != 3);

	}

}
