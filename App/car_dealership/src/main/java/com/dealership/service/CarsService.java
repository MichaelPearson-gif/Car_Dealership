package com.dealership.service;

import java.util.List;

import com.dealership.exceptions.BusinessException;
import com.dealership.model.Cars;

public interface CarsService {

	// Create a new car on the lot
	public int newCar(Cars car) throws BusinessException;
	
	// Customers can view all their cars
	public List<Cars> customerCars(String username) throws BusinessException;
	
	// Get all cars on the lot
	public List<Cars> allCarsOnLot() throws BusinessException;
	
	// The following methods will be called in the offers service layer
	
	// When offers are accepted
	public int carUpdate(int carId, String username) throws BusinessException;
	
	// When employee takes a car off the lot
	public int carUpdate(int carId) throws BusinessException;
}
