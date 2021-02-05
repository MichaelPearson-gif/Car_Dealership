package com.dealership.dao;

import java.util.List;

import com.dealership.exceptions.BusinessException;
import com.dealership.model.Payment;

public interface PaymentDAO {

	// Make a payment
	public int makePayment(Payment payment) throws BusinessException;
	
	// Customer can view remaining payments on a car
	public void remainingPayments(int carId) throws BusinessException;
	
	// Employee can view all payments on a car
	public List<Payment> allPayments(int carId) throws BusinessException;
	
}
