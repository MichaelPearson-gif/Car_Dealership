package com.dealership.service.impl;

import java.util.List;

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

	@Override
	public List<Cars> customerCars(String username) throws BusinessException {

		// Create the list object and set it to null
		List<Cars> myCars = null;
		
		// Override the list with the query results
		myCars = carsDAO.customerCars(username);
		
		return myCars;
	}

	@Override
	public List<Cars> allCarsOnLot() throws BusinessException {
		
		// Create a list object to store all cars on the lot
		List<Cars> allCars = null;
		
		// Override the list with the query results
		allCars = carsDAO.allCarsOnLot();
		
		return allCars;
	}

	@Override
	public int carUpdate(int carId, String username) throws BusinessException {
		
		// Value to return whether the update was successful or not
		int c = 0;
		
		// Override c to equal the correct carUpdate method from the dao layer
		c = carsDAO.carUpdate(carId, username);
		
		return c;
	}

	@Override
	public int carUpdate(int carId) throws BusinessException {
		
		// Value to return whether the update was successful or not
		int c = 0;
		
		// Override c to equal the correct carUpdate method from the dao layer
		c = carsDAO.carUpdate(carId);
		
		return c;
	}

}
