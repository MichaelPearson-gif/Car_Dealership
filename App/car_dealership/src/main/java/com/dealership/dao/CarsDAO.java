package com.dealership.dao;

import java.util.List;

import com.dealership.exceptions.BusinessException;
import com.dealership.model.Cars;

public interface CarsDAO {

	// Create a new car on the lot
	public int newCar(Cars car) throws BusinessException;
	
	// Get all cars owned by a customer
	public List<Cars> customerCars(String username) throws BusinessException;
	
	// Get all cars on the lot
	public List<Cars> allCarsOnLot() throws BusinessException;
	
	// The next two methods are a good example of method overloading
	
	// Update a car when an offer is accepted
	public int carUpdate(int carId, String username) throws BusinessException;
	
	// Update a car when an employee takes a car off the lot
	public int carUpdate(int carId) throws BusinessException;
	
}
