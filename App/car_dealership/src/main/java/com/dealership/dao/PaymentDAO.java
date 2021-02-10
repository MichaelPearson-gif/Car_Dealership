package com.dealership.dao;

import java.util.List;

import com.dealership.exceptions.BusinessException;
import com.dealership.model.Payment;

public interface PaymentDAO {

	// Make Payment
	public int makePayment(Payment payment) throws BusinessException;
	
	// Get the latest payment info
	public Payment latestPayment(int carId) throws BusinessException;
	
	// Employee can view all payments on a car
	public List<Payment> allPayments(int carId) throws BusinessException;
	
}
