package com.dealership.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dealership.dao.PaymentDAO;
import com.dealership.dao.dbutil.PostgresqlConnection;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.Payment;

public class PaymentDAOImpl implements PaymentDAO {

	// Logger variable
	Logger log = Logger.getLogger(PaymentDAOImpl.class);

	@Override
	public int makePayment(Payment payment) throws BusinessException {

		int c = 0;
		LocalDate ld = LocalDate.now();
		Date sqlDate = Date.valueOf(ld);

		// Connect and update the DB
		try (Connection connection = PostgresqlConnection.getConnection()) {

			// SQL statement
			String sql = "INSERT INTO car_dealership.payments(car_id, amount, total_payment, monthly_payment, remaining_payments, date) VALUES(?, ?, ?, ?, ?, ?)";

			// Make a PreparedStatement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, payment.getCarId());
			preparedStatement.setDouble(2, payment.getAmount());
			preparedStatement.setDouble(3, payment.getTotalPayment());
			preparedStatement.setDouble(4, payment.getMonthlyPayment());
			preparedStatement.setInt(5, payment.getRemainingPayments());
			preparedStatement.setDate(6, sqlDate);

			// Override c with the query execution
			c = preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
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
		try (Connection connection = PostgresqlConnection.getConnection()) {

			// SQL statement
			// ORDER BY date DESC: Puts all the matching records in descending order from
			// new to old
			// LIMIT 1: Limits the number of records we get is only 1
			String sql = "SELECT * FROM car_dealership.payments " + "WHERE car_id = ? ORDER BY date DESC LIMIT 1";

			// Make a PreparedStatement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, carId);

			// Create the ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			// Populate the payment object
			if (resultSet.next()) {
				payment = new Payment(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDouble(3),
						resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getInt(6), resultSet.getDate(7));
			}

		} catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}

		return payment;
	}

	@Override
	public List<Payment> allPayments(int carId) throws BusinessException {

		// Create a list to store all payments for a car
		List<Payment> paymentList = new ArrayList<>();

		// Connect and query the DB
		try (Connection connection = PostgresqlConnection.getConnection()) {

			// SQL statement
			String sql = "SELECT * FROM car_dealership.payments WHERE car_id = ?";

			// Make a PreparedStatement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, carId);

			// Create the ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			// Loop through the ResultSet to add each result to the paymentList list
			while (resultSet.next()) {
				paymentList.add(new Payment(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDouble(3),
						resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getInt(6), resultSet.getDate(7)));
			}

			// Condition where there are no payments
//			if(paymentList.size() == 0) {
//				log.error("There is no payment history for the car id: " + carId);
//			}

		} catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}

		return paymentList;
	}

}
