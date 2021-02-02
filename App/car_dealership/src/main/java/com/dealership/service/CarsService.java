package com.dealership.service;

import java.util.List;

import com.dealership.exceptions.BusinessException;
import com.dealership.model.Cars;

public interface CarsService {

	// Create a new car on the lot
	public int newCar(Cars car) throws BusinessException;
	
	// Customers can view all their cars
	public List<Cars> customerCars(String username) throws BusinessException;
}
