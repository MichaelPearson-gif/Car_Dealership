package com.dealership.test;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.dealership.dao.impl.PaymentDAOImpl;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.Payment;
import com.dealership.service.PaymentService;
import com.dealership.service.impl.PaymentServiceImpl;

class PaymentServiceImplTest {
	
	// Inject Mocks
	@InjectMocks
	private static PaymentService paymentService;
	
	//Mock the DAO implementation
	@Mock
	private PaymentDAOImpl paymentDAOImpl;

	// Setup the paymentService to the implementation class
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		paymentService = new PaymentServiceImpl();
	}

	// Before each test, mock the DAO implementation
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	// Write all test cases
	@Test
	void testRemainingPayment() {
		
		// Create a dummy carId to pass through the test
		int carId = 105;
		
		// Create a dummy payment object to be the mocked return
		Payment payment = new Payment(10000, 105, 116.67, 7000.00, 116.67, 10, new Date(3242021));
		
		try {
			
			// Tell Mockito what to mock for this test
			Mockito.when(paymentDAOImpl.latestPayment(carId)).thenReturn(payment);
			
			// Create an int value to store the result of the paymentService.remainingPayment method
			int returnedNumPayments = paymentService.remainingPayment(carId);
			
			// Use assertEquals to test if it works
			Assertions.assertEquals(10, returnedNumPayments);
			
		}catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	void testAllPayments() {
		fail("Not yet implemented");
	}

	@Test
	void testMakePayment() {
		fail("Not yet implemented");
	}

}
