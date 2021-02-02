package com.dealership.service.impl;

import com.dealership.dao.CarsDAO;
import com.dealership.dao.impl.CarsDAOImpl;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.Cars;
import com.dealership.service.CarsService;

public class CarsServiceImpl implements CarsService {

	// Create the connection between the service layer and the dao layer
	private CarsDAO carsDAO = new CarsDAOImpl();
	
	@Override
	public int newCar(Cars car) throws BusinessException {
		// Value to return whether the update was successful or not
		int c = 0;
		
		// Set c equal to the carsDAO object
		c = carsDAO.newCar(car);
		
		return c;
	}

}
