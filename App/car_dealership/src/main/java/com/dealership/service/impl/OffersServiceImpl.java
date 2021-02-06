package com.dealership.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dealership.dao.OffersDAO;
import com.dealership.dao.impl.OffersDAOImpl;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.Offers;
import com.dealership.model.Payment;
import com.dealership.service.CarsService;
import com.dealership.service.OffersService;
import com.dealership.service.PaymentService;

public class OffersServiceImpl implements OffersService {
	
	// Logger variable
	Logger log = Logger.getLogger(OffersServiceImpl.class);
	
	// Connection between service layer and dao layer
	private OffersDAO offersDAO = new OffersDAOImpl();
	
	// Connection between service layers
	private CarsService carsService = new CarsServiceImpl();
	private PaymentService paymentService = new PaymentServiceImpl();

	// Customer makes an offer on a car
	@Override
	public int makeOffer(Offers offer) throws BusinessException {
		
		// Value to return whether the update was successful or not
		int c = 0;
		
		// Set c equal to the offersDAO object
		c = offersDAO.makeOffer(offer);
		
		return c;
	}

	// Employee views all pending offers
	@Override
	public List<Offers> allOffers() throws BusinessException {
		
		// Create a list of all pending offers
		List<Offers> pendingOffers = new ArrayList<>();
		
		// Extract the data from db
		pendingOffers = offersDAO.allOffers();
		
		return pendingOffers;
	}

	// Employee views all offers by carId
	@Override
	public List<Offers> carOffers(int carId) throws BusinessException {
		
		// Create a list 
		List<Offers> filteredOffers = offersDAO.allOffers();
		
		// Remove all offers that do not contain the requested car id
		filteredOffers.removeIf((o) -> o.getCarId() != carId);
		
		return filteredOffers;
	}

	// Employee updates status of an offer
	@Override
	public void statusUpdate(int offerId, String status) throws BusinessException {
		
		// If employee approves the offer, make the following updates
		if(status == "Approved") {
			
			// Create variables to store the car id and the username on the offer
			int carId;
			String username;
			
			//---------------------------------------------------------------
			// Creating a Payment Object and getting the offer amount
			
			// Create a Payment object so as to create an initial payment
			Payment initPayment = new Payment();
			
			// Create an offer object to get all the approved offer's data
			List<Offers> approvedList = offersDAO.allOffers();
			approvedList.removeIf((o) -> o.getOfferId() != offerId);
			
			Offers approvedOffer = new Offers();
			
			// Loop through the list and set it to the approvedOffer object
			// Only need the offer amount for the payment object
			for(Offers o : approvedList) {
				approvedOffer.setOffer(o.getOffer());
			}
			
			//-----------------------------------------------------------------
			
			// Employee making updates
			offersDAO.statusUpdate(offerId, status);
			
			// Get the car id and username
			carId = offersDAO.getOfferCarId(offerId);
			username = offersDAO.getOfferUsername(offerId);
			
			// System updates
			
			//-----------------------------------------------------------------
			// Setting the offer amount and car id to the payment object and sending it to the 
			// payment service layer
			
			initPayment.setCarId(carId);
			initPayment.setTotalPayment(approvedOffer.getOffer());
			paymentService.makePayment(initPayment);
			
			
			//-----------------------------------------------------------------
			
			// Update the car
			carsService.carUpdate(carId, username);
			
			// Get a list of all other offers for the car id
			List<Offers> otherOffers = carOffers(carId);
			
			// Check size of the otherOffers list
			if(otherOffers.size() > 0) {
				
				// Update the status of other offers for this car to Declined 
				for(Offers offer : otherOffers) {
					offersDAO.statusUpdate(offer);
				}
				
				// Print out a message saying that the updates was successful
				log.info("All other offers have been updated");
				
			}else {
				// Print out a message saying that the updates was successful
				log.info("All updates were successful");
			}
			
		}else if(status == "Declined") {
			// Update the status
			offersDAO.statusUpdate(offerId, status);
			
			// Print out a message saying that the updates was successful
			log.info("All updates were successful");
		}else {
			// Print a message to the employee about an invalid option
			log.info("Invalid status! Please try again.");
		}
		
	}

}
