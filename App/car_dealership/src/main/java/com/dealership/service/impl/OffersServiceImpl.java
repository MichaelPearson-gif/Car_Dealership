package com.dealership.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.dealership.dao.OffersDAO;
import com.dealership.dao.impl.OffersDAOImpl;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.Offers;
import com.dealership.service.CarsService;
import com.dealership.service.OffersService;

public class OffersServiceImpl implements OffersService {
	
	// Connection between service layer and dao layer
	private OffersDAO offersDAO = new OffersDAOImpl();
	
	// Connection between offers service layer and cars service layer
	private CarsService carsService = new CarsServiceImpl();

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
		// TODO Auto-generated method stub
		return null;
	}

	// Employee updates status of an offer
	@Override
	public int statusUpdate(int offerId, String status) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
