package com.dealership.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dealership.dao.OffersDAO;
import com.dealership.dao.dbutil.PostgresqlConnection;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.Offers;

public class OffersDAOImpl implements OffersDAO {

	// Logger variable
	Logger log = Logger.getLogger(OffersDAOImpl.class);
	
	// Customer makes an offer on a car
	@Override
	public int makeOffer(Offers offer) throws BusinessException {
		
		// Value to return whether the update was successful or no
		int c = 0;
		
		// Status of new offers are always going to be pending
		offer.setStatus("Pending");
		
		// Connect and update the DB
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			// SQL statement
			String sql = "INSERT INTO car_dealership.offers(username, car_id, offer, status) "
					+ "VALUES(?, ?, ?, ?)";
			
			// Make the PreparedStatement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, offer.getUsername());
			preparedStatement.setInt(2, offer.getCarId());
			preparedStatement.setDouble(3, offer.getOffer());
			preparedStatement.setString(4, offer.getStatus());
			
			c = preparedStatement.executeUpdate();
			
		}catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}
		
		return c;
	}

	// Employee views all pending offers
	@Override
	public List<Offers> allOffers() throws BusinessException {
		
		// Create a pending offers list
		List<Offers> pendingOffers = new ArrayList<>();
		
		// Connect and update the DB
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			// SQL statement
			String sql = "SELECT * FROM car_dealership.offers WHERE status = 'Pending'";
			
			// Make the PreparedStatement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			// Create the ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// Loop through the ResultSet to add each result to the pendingOffers list
			while(resultSet.next()) {
				Offers offer = new Offers();
				offer.setOfferId(resultSet.getInt("offer_id"));
				offer.setUsername(resultSet.getString("username"));
				offer.setCarId(resultSet.getInt("car_id"));
				offer.setOffer(resultSet.getDouble("offer"));
				offer.setStatus(resultSet.getString("status"));
				
				// Adding each offer object to the list
				pendingOffers.add(offer);
			}
			
			// Print out a message to the employee if they have no pending offers
			if(pendingOffers.size() == 0) {
				log.info("There are no pending offers on cars at this time");
			}
			
		}catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}
		
		return pendingOffers;
	}

	// Employee views all offers by carId
	@Override
	public List<Offers> carOffers(int carId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	// Employee updates status of an offer
	@Override
	public int statusUpdate(int carId, String answer) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// System updates all offers on the same car to decline, once an offer is accepted
	// This will be an overloaded method
	@Override
	public int statusUpdate(int carId) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
