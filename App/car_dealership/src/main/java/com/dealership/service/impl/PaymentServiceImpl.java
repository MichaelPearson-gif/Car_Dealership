package com.dealership.service.impl;

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
	public void remainingPayment(int carId) throws BusinessException {
		
		// Create a variable to store the remaining payments and an object of Payment
		// Both of which will initially be null
		int numPayments;
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
		// TODO Auto-generated method stub
		return 0;
	}

}
