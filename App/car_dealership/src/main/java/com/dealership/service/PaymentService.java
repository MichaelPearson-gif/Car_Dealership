package com.dealership.service;

import java.util.List;

import com.dealership.exceptions.BusinessException;
import com.dealership.model.Payment;

public interface PaymentService {

	// Customers can view remaining payments
	public int remainingPayment(int carId) throws BusinessException;
	
	// Employee can view all payments on a car
	public List<Payment> allPayments(int carId) throws BusinessException;
	
	// Make a payment when an offer is approved
	public int makePaymentByOffer(Payment payment) throws BusinessException;
	
	// Customer makes a payment
	public int customerPayment(Payment payment) throws BusinessException;
	
}
