package com.dealership.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;

import com.dealership.dao.PaymentDAO;
import com.dealership.dao.dbutil.PostgresqlConnection;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.Payment;

public class PaymentDAOImpl implements PaymentDAO {
	
	// Logger variable
	Logger log = Logger.getLogger(PaymentDAOImpl.class);
	
	// Import LocalDate
	LocalDate ld = LocalDate.now();
	// Convert to a sql.Date format
	Date date = Date.valueOf(ld);

	@Override
	public int makePayment(Payment payment) throws BusinessException {
		
		int c = 0;
		
		// Calculate the remaining payments
		// Assume that each payment is the same as the calculated monthly amount
		// Thus the remaining payments is just 1 less than the previous remaining amount
		int newRemainingAmount = payment.getRemainingPayments() - 1;
		
		// Connect and update the DB
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			// Using an if statement to make sure the customer does not accidentally pay an for an extra month
			if(newRemainingAmount < 0) {
				log.info("The payment was rejected because the car is already paid off");
			}else {
			
				// SQL statement
				String sql = "INSERT INTO car_dealership.payments VALUES(DEFAULT, ?, ?, ?, ?, ?, ?)";

				// Make a PreparedStatement
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, payment.getCarId());
				preparedStatement.setDouble(2, payment.getAmount());
				preparedStatement.setDouble(3, payment.getTotalPayment());
				preparedStatement.setDouble(4, payment.getMonthlyPayment());
				preparedStatement.setInt(5, newRemainingAmount);
				preparedStatement.setDate(6, date);
				
				// Override c with the query execution
				c = preparedStatement.executeUpdate();
				
			}
			
		}catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}
		
		return c;
	}

	@Override
	public Payment latestPayment(int carId) throws BusinessException {
		
		// Create a variable to store and return query
		Payment payment = null;
		
		// Connect and query the DB
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			// SQL statement
			// ORDER BY date DESC: Puts all the matching records in descending order from new to old
			// LIMIT 1: Limits the number of records we get is only 1
			String sql = "SELECT * FROM car_dealership.payments "
					+ "WHERE car_id = ? ORDER BY date DESC LIMIT 1";
			
			// Make a PreparedStatement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, carId);
			
			// Create the ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// Populate the payment object
			if(resultSet.next()) {
				payment = new Payment(
						resultSet.getInt(1),
						resultSet.getInt(2),
						resultSet.getDouble(3),
						resultSet.getDouble(4),
						resultSet.getDouble(5),
						resultSet.getInt(6),
						resultSet.getDate(6)
						);
			}
			
		}catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}
		
		return payment;
	}

	@Override
	public List<Payment> allPayments(int carId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
