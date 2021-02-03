package com.dealership.dao.impl;

import java.util.List;

import com.dealership.dao.OffersDAO;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.Offers;

public class OffersDAOImpl implements OffersDAO {

	@Override
	public int makeOffer(Offers offer) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Offers> allOffers() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offers> carOffers(int carId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int statusUpdate(int carId, String answer) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int statusUpdate(int carId) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
