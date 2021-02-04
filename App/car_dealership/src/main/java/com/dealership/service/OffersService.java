package com.dealership.service;

import java.util.List;

import com.dealership.exceptions.BusinessException;
import com.dealership.model.Offers;

public interface OffersService {
	
	// Customer makes an offer on a car
	public int makeOffer(Offers offer) throws BusinessException;
	
	// Employee views all pending offers
	public List<Offers> allOffers() throws BusinessException;
	
	// Employee views all offers by carId
	public List<Offers> carOffers(int carId) throws BusinessException;
	
	// Employee updates status of an offer
	public int statusUpdate(int offerId, String status) throws BusinessException;
}
