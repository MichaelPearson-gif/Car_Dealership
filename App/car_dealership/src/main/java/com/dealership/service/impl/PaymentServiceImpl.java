package com.dealership.service.impl;

import java.util.Collections;
import java.util.Comparator;
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
		Payment payment = null;
		
		try {
			// Get the last payment info for this car
			payment = paymentDAO.latestPayment(carId);
			
			// Retrieve the number of payments remaining for the car
			numPayments = payment.getRemainingPayments();
			
			// Print out to the customer their remaining payments
			System.out.println("You have " + numPayments + " left of $" + payment.getMonthlyPayment());
			
		}catch(NullPointerException e) {
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
			
			
		}catch(NullPointerException e) {
			log.error(e.getMessage());
			throw new BusinessException("payment value is null");
		}
		
		return paymentList;
	}

	@Override
	public int makePayment(Payment payment) throws BusinessException {
		
		int c = 0;
		
		List<Payment> pastPayments = allPayments(payment.getCarId());
		
		if(pastPayments.size() == 0) {
			// When an offer is accepted, create a payment record with the amount of 0
			// This way there will be a record of the monthly payments
			payment.setAmount(0.00);
			
			// Each customer will be making payments over the course of 5 years = 60 months
			double monthlyPayment = payment.getTotalPayment() / 60;
			payment.setMonthlyPayment(monthlyPayment);
			payment.setRemainingPayments(60);
			
			c = paymentDAO.makePayment(payment);
			
		}else {
			
			// When a customer makes a payment
			// Get the latest payment record
			Collections.sort(pastPayments, new Comparator<Payment>() {
				public int compare(Payment o1, Payment o2) {
					return Integer.compare(o1.getPaymentId(), o2.getPaymentId());
				}
			});
			
			Payment recentPayment = pastPayments.stream().max((x, y) -> x.getPaymentId() - y.getPaymentId()).get();
			
			// Now grab the total payment, monthly payment, and remaining payments info
			payment.setTotalPayment(recentPayment.getTotalPayment());
			payment.setMonthlyPayment(recentPayment.getMonthlyPayment());
			payment.setRemainingPayments(recentPayment.getRemainingPayments());
			
			// Now send the info to the dao layer
			c = paymentDAO.makePayment(payment);
			
		}
		
		return c;
	}

}
