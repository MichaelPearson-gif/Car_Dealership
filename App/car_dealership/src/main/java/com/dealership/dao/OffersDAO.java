package com.dealership.dao;

import java.util.List;

import com.dealership.exceptions.BusinessException;
import com.dealership.model.Offers;

public interface OffersDAO {

	// Customer makes an offer on a car
	public int makeOffer(Offers offer) throws BusinessException;
	
	// Employee views all pending offers
	public List<Offers> allOffers() throws BusinessException;
	
	// Employee views all offers by carId
	public List<Offers> carOffers(int carId) throws BusinessException;
	
	// Employee updates status of an offer
	public int statusUpdate(int offerId, String status) throws BusinessException;
	
	// Get the car_id of the approved offer
	public int getOfferCarId(int offerId) throws BusinessException;
	
	// Get the username of the approved offer
	public String getOfferUsername(int offerId) throws BusinessException;
	
	// The following methods are for when an employee approves an offer
	
	// System updates all offers on the same car to decline, once an offer is accepted
	// This will be an overloaded method
	public int statusUpdate(int offerId) throws BusinessException;
	
}
