package com.dealership.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dealership.dao.CarsDAO;
import com.dealership.dao.dbutil.PostgresqlConnection;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.Cars;

public class CarsDAOImpl implements CarsDAO {
	
	// Create Logger Object
	Logger log = Logger.getLogger(CarsDAOImpl.class);

	@Override
	public int newCar(Cars car) throws BusinessException {
		// Value to return whether the update was successful or not
		int c = 0;
		
		// Since this is a new car on the lot, I can set a default value for the owner_name and lot
		car.setOwnerStatus("None");
		car.setLot("on");
		
		// Connect and update the DB
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			// SQL statement
			String sql = "INSERT INTO car_dealership.cars(owner_name, lot, make, model, color, price)"
					+ "VALUES(?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, car.getOwnerStatus());
			preparedStatement.setString(2, car.getLot());
			preparedStatement.setInt(3, car.getMake());
			preparedStatement.setString(4, car.getModel());
			preparedStatement.setString(5, car.getColor());
			preparedStatement.setDouble(6, car.getPrice());
			
			c = preparedStatement.executeUpdate();
			
		}catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}
		
		return c;
	}

	@Override
	public List<Cars> customerCars(String username) throws BusinessException {
		
		// Create a list
		List<Cars> myCars = new ArrayList<>();
		
		// Connect and querry the DB
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			// SQL query statement
			String sql = "SELECT * FROM car_dealership.cars WHERE username = ?";
			
			// Make the Prepared Statement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			// Insert the value to search by
			preparedStatement.setString(1, username);
			
			// Create the result set to execute the query
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// Loop through the resultSet and create a new car object for each record in the resultSet
			while(resultSet.next()) {
				Cars car = new Cars();
				car.setCarId(resultSet.getInt("car_id"));
				car.setOwnerStatus(resultSet.getString("owner_status"));
				car.setLot(resultSet.getString("lot"));
				car.setMake(resultSet.getInt("make"));
				car.setModel(resultSet.getString("model"));
				car.setColor(resultSet.getString("color"));
				car.setPrice(resultSet.getDouble("price"));
				car.setUsername(resultSet.getString("username"));
				
				// Add each car object to the list
				myCars.add(car);
			}
			
			// Print out a message to the user if they have no cars they own
			if(myCars.size() == 0) {
				log.info("Sorry you do not have any cars purchased from our dealership. You should go check out all the awesome cars we have in store for you!");
			}
			
		}catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}
		
		return myCars;
	}

	@Override
	public List<Cars> allCarsOnLot() throws BusinessException {
		// Create a list
		List<Cars> allCars = new ArrayList<>();
		
		// Connect and querry the DB
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			// SQL query statement
			String sql = "SELECT car_id, make, model, color, price FROM car_dealership.cars WHERE lot = on";
			
			// Make the Prepared Statement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			// Create the result set to execute the query
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// Loop through the resultSet and create a new car object for each record in the resultSet
			while(resultSet.next()) {
				Cars car = new Cars();
				car.setCarId(resultSet.getInt("car_id"));
				car.setMake(resultSet.getInt("make"));
				car.setModel(resultSet.getString("model"));
				car.setColor(resultSet.getString("color"));
				car.setPrice(resultSet.getDouble("price"));
				
				// Add each car object to the list
				allCars.add(car);
			}
			
			// Print out a message to the user if they have no cars they own
			if(allCars.size() == 0) {
				log.info("Sorry we do not have any cars on the lot at the moment");
			}
			
		}catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}
		
		return allCars;
	}

	// When offers are accepted
	@Override
	public int carUpdate(int carId, String username) throws BusinessException {
		int c = 0;
		
		// Connect and update the DB
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			// SQL statement. Using update join and subqueries to complete it
			String sql = "UPDATE car_dealership.cars SET owner_status = Owned, lot = 'off', username =? WHERE car_id = ?";
			
			// Make the prepared statement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, carId);
			
			// Execute the update query
			c = preparedStatement.executeUpdate();
			
		}catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}
		
		return c;
	}
	
	// The following methods will go in the Offers service layer and NOT the cars service layer since these updates will be made when employees look at offers.

	// When employee takes a car off the lot
	@Override
	public int carUpdate(int carId) throws BusinessException {
		int c = 0;
		
		// Connect and update the DB
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			// SQL statement
			String sql = "UPDATE car_dealership.cars SET lot = 'off' WHERE car_id = ?";
			
			// Make the prepared statement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, carId);
			
			// Execute the update query
			c = preparedStatement.executeUpdate();
			
		}catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}
		
		return c;
	}

}
