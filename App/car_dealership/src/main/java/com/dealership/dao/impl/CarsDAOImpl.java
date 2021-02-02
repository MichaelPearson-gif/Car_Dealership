package com.dealership.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		car.setOwner("None");
		car.setLot("on");
		
		// Connect and update the DB
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			// SQL statement
			String sql = "INSERT INTO car_dealership.cars(owner_name, lot, make, model, color, price)"
					+ "VALUES(?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, car.getOwner());
			preparedStatement.setString(2, car.getLot());
			preparedStatement.setInt(3, car.getMake());
			preparedStatement.setString(4, car.getModel());
			preparedStatement.setString(5, car.getColor());
			preparedStatement.setDouble(6, car.getPrice());
			
			c = preparedStatement.executeUpdate();
			
		}catch (ClassNotFoundException | SQLException e) {
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}
		
		return c;
	}

	@Override
	public List<Cars> customerCars(String username) throws BusinessException {
		
		return null;
	}

	@Override
	public List<Cars> allCarsOnLot() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
