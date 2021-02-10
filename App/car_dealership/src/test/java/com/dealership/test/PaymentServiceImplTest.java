package com.dealership.test;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		
		// Create a dummy variable for the carId to pass through the test
		int carId = 105;
		
		// Create a list of payments to be expected to be returned
		List<Payment> myPayments = new ArrayList<>(Arrays.asList(new Payment(10000, 105, 116.67, 7000.00, 116.67, 59, new Date(3242021)),
				new Payment(10000, 105, 116.67, 7000.00, 116.67, 58, new Date(4242021)),
				new Payment(10000, 105, 116.67, 7000.00, 116.67, 57, new Date(5242021))));
		
		try {
			
			// Tell Mockito what to mock for this test
			Mockito.when(paymentDAOImpl.allPayments(carId)).thenReturn(myPayments);
			
			// Create a list that will be set to the allPayments service method
			List<Payment> returnedPayments = paymentService.allPayments(carId);
			
			// Use assertEquals to test if the size of the list is correct
			Assertions.assertEquals(3, returnedPayments.size());
			
		}catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	void testMakePayment() {
		fail("Not yet implemented");
	}

}
