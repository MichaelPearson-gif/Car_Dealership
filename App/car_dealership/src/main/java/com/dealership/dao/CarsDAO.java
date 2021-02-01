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
	
	// Update a car
	// field represents the column in the db that is being updated
	// value is what is being placed into the db.
	public int carUpdate(int carId, String field, String value) throws BusinessException;
	
}
