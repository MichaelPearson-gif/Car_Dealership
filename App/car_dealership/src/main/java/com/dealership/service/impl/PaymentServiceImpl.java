package com.dealership.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.apache.log4j.Logger;
import com.dealership.dao.PaymentDAO;
import com.dealership.dao.impl.PaymentDAOImpl;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.Payment;
import com.dealership.service.PaymentService;

public class PaymentServiceImpl implements PaymentService {

	// Logger variable
	Logger log = Logger.getLogger(PaymentServiceImpl.class);

	// Instance of the dao layer
	private static PaymentDAO paymentDAO = new PaymentDAOImpl();

	@Override
	public int remainingPayment(int carId) throws BusinessException {

		// Create a variable to store the remaining payments and an object of Payment
		// Both of which will initially be null
		int numPayments = 0;
		Payment payment = new Payment();

		try {
			// Get the last payment info for this car
			payment = paymentDAO.latestPayment(carId);

			// Retrieve the number of payments remaining for the car
			numPayments = payment.getRemainingPayments();

		} catch (NullPointerException e) {
			log.error(e.getMessage());
			throw new BusinessException("payment value is null");
		}
		return numPayments;

	}

	@Override
	public List<Payment> allPayments(int carId) throws BusinessException {

		List<Payment> paymentList = null;

		try {
			// Get the last payment info for this car
			paymentList = paymentDAO.allPayments(carId);

		} catch (NullPointerException e) {
			log.error(e.getMessage());
			throw new BusinessException("payment value is null");
		}

		return paymentList;
	}

	@Override
	public int makePaymentByOffer(Payment payment) throws BusinessException {

		int c = 0;

		// When an offer is accepted, create a payment record with the amount of 0
		// This way there will be a record of the monthly payments
		payment.setAmount(0.00);

		// Each customer will be making payments over the course of 5 years = 60 months
		double monthlyPayment = payment.getTotalPayment() / 60;
		
		// BigDecimal will scale the monthlyPayment to 2 digits and apply RoundingMode.HALF_UP
		BigDecimal bd = new BigDecimal(monthlyPayment).setScale(2, RoundingMode.HALF_UP);
		payment.setMonthlyPayment(bd.doubleValue());
		payment.setRemainingPayments(60);

		c = paymentDAO.makePayment(payment);

		return c;
	}

	@Override
	public int customerPayment(Payment payment) throws BusinessException {

		int c = 0;

		// Get the latest payment info
		Payment recentPayment = paymentDAO.latestPayment(payment.getCarId());

		// Now grab the total payment, monthly payment, and remaining payments info
		payment.setTotalPayment(recentPayment.getTotalPayment());
		payment.setMonthlyPayment(recentPayment.getMonthlyPayment());
		
		// Calculate the remaining payments
		// Assume that each payment is the same as the calculated monthly amount
		// Thus the remaining payments is just 1 less than the previous remaining amount
		int newRemainingPayment = recentPayment.getRemainingPayments() - 1;
		payment.setRemainingPayments(newRemainingPayment);

		// Now send the info to the DAO layer
		c = paymentDAO.makePayment(payment);

		return c;
	}

}
