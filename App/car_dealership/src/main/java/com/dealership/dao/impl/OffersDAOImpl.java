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
		
		// Value to return whether the update was successful or not
		int c = 0;
		
		// Status of new offers are always going to be pending
		offer.setStatus("Pending");
		
		// Connect and update the DB
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			// SQL statement
			String sql = "INSERT INTO car_dealership.offers(username, car_id, offer, status) "
					+ "VALUES(DEFAULT, ?, ?, ?, ?)";
			
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
				log.error("There are no pending offers");
			}
			
		}catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}
		
		return pendingOffers;
	}
	
	// Get the car_id of the approved offer
	@Override
	public int getOfferCarId(int offerId) throws BusinessException {
		
		// carId variable
		int carId = 0;
		
		// Connect and update to the DB
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			// SQL statement
			String sql = "SELECT car_id FROM car_dealership.offers WHERE offer_id = ?";
			
			// Make a PreparedStatement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, offerId);
			
			// Make the ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// set the variable count equal to the the value of the resultSet
			if(resultSet.next()) {
				carId = resultSet.getInt("count_id");
			}
			
		}catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}
		
		return carId;
	}

	// Get the username of the approved offer
	@Override
	public String getOfferUsername(int offerId) throws BusinessException {
		
		// carId variable
		String username = null;
		
		// Connect and update to the DB
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			// SQL statement
			String sql = "SELECT username FROM car_dealership.offers WHERE offer_id = ?";
			
			// Make a PreparedStatement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, offerId);
			
			// Make the ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// set the variable count equal to the the value of the resultSet
			if(resultSet.next()) {
				username = resultSet.getString("username");
			}
			
		}catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}
		
		return username;
	}

	// Employee updates status of an offer
	@Override
	public int statusUpdate(int offerId, String status) throws BusinessException {
		
		// Value to return whether the update was successful or not
		int c = 0;
		
		// Connect and update to the DB
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			// SQL statement
			String sql = "UPDATE car_dealership.offers SET status = ? WHERE offer_id = ?";
			
			// Make the PreparedStatement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, offerId);
			
			c = preparedStatement.executeUpdate();
			
		}catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}
		
		return c;
	}
	
	// System updates all offers on the same car to decline, once an offer is accepted
	// This will be an overloaded method
	@Override
	public int statusUpdate(Offers offer) throws BusinessException {
		
		// Value to return whether the update was successful or not
		int c = 0;
		
		// Connect and update to the DB
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			// SQL statement
			String sql = "UPDATE car_dealership.offers SET status = 'Declined' WHERE offer_id = ?";
			
			// Make the PreparedStatement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, offer.getOfferId());
			
			c = preparedStatement.executeUpdate();
			
		}catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}
		
		return c;
	}

}
