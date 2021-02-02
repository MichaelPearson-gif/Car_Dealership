package com.dealership.service;

import com.dealership.exceptions.BusinessException;
import com.dealership.model.Cars;

public interface CarsService {

	// Create a new car on the lot
	public int newCar(Cars car) throws BusinessException;
}
