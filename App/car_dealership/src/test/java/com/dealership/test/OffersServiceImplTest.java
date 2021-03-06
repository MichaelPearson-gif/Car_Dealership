package com.dealership.test;

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

import com.dealership.dao.impl.OffersDAOImpl;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.Offers;
import com.dealership.service.OffersService;
import com.dealership.service.impl.OffersServiceImpl;

class OffersServiceImplTest {
	
	// Inject Mocks
	@InjectMocks
	private static OffersService offersService;
	
	// Mock the DAO implementation
	@Mock
	private OffersDAOImpl offersDAOImpl;

	// Setup the offerService to the implementation class
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		offersService = new OffersServiceImpl();
	}

	// Before each test, mock the DAO implementation
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	// Write all test cases
	@Test
	void testMakeOffer() {
		
		// Create a dummy offer object to pass through the test
		Offers offer = new Offers(1000, "Drakebell", 104, 10000.00, "Pending");
		
		try {
			
			// Tell Mockito what to Mock
			Mockito.when(offersDAOImpl.makeOffer(offer)).thenReturn(1);
			
			// Create an int value to store the makeOffer service method
			int c = offersService.makeOffer(offer);
			
			// Use assertEquals to check if the insert was successful
			Assertions.assertEquals(1, c);
			
		}catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// Test to make sure the status for each offer in this list says Pending
	@Test
	void testAllOffers() {
		
		// Create a dummy list of pending offers
		List<Offers> myOffers = new ArrayList<>(Arrays.asList(new Offers(1000, "Drakebell", 104, 10000.00, "Pending"),
				new Offers(1001, "TDog", 106, 5000.00, "Pending"),
				new Offers(1002, "starGuy", 103, 20000.00, "Pending")));
		
		try {
			
			// Tell Mockito what to Mock
			Mockito.when(offersDAOImpl.allOffers()).thenReturn(myOffers);
			
			// Create a list object to make the call to the allOffers service method
			List<Offers> returnedOffers = offersService.allOffers();
			
			// Loop through the list to test that the status says Pending
			for(Offers offer : returnedOffers) {
				Assertions.assertEquals("Pending", offer.getStatus());
			}
			
		}catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// Test to make sure the size of the list is correct
	@Test
	void testAllOffersSize() {
		
		// Create a dummy list of pending offers
		List<Offers> myOffers = new ArrayList<>(Arrays.asList(new Offers(1000, "Drakebell", 104, 10000.00, "Pending"),
				new Offers(1001, "TDog", 106, 5000.00, "Pending"),
				new Offers(1002, "starGuy", 103, 20000.00, "Pending")));
		
		try {
			
			// Tell Mockito what to Mock
			Mockito.when(offersDAOImpl.allOffers()).thenReturn(myOffers);
			
			// Create a list object to make the call to the allOffers service method
			List<Offers> returnedOffers = offersService.allOffers();
			
			// Test the size of the list
			Assertions.assertEquals(myOffers.size(), returnedOffers.size());
			
			
		}catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testCarOffers() {
		
		// Create a dummy car id
		int carId = 104;
		
		// Create a dummy list of pending offers
		List<Offers> myOffers = new ArrayList<>(Arrays.asList(new Offers(1000, "Drakebell", 104, 10000.00, "Pending"),
				new Offers(1001, "TDog", 104, 5000.00, "Pending"),
				new Offers(1002, "starGuy", 104, 8000.00, "Pending")));
		
		try {
			
			// Tell Mockito what to Mock
			Mockito.when(offersDAOImpl.allOffers()).thenReturn(myOffers);
			
			// Create a list object to make the call to the allOffers service method
			List<Offers> returnedOffers = offersService.carOffers(carId);
			
			// Loop through the list to test that the status says Pending
			for(Offers offer : returnedOffers) {
				Assertions.assertEquals(104, offer.getCarId());
			}
			
		}catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	// Test the size of car offers
	@Test
	void testCarOffersSize() {
		
		// Create a dummy car id
		int carId = 104;
		
		// Create a dummy list of pending offers
		List<Offers> myOffers = new ArrayList<>(Arrays.asList(new Offers(1000, "Drakebell", 104, 10000.00, "Pending"),
				new Offers(1001, "TDog", 104, 5000.00, "Pending"),
				new Offers(1002, "starGuy", 104, 8000.00, "Pending")));
		
		try {
			
			// Tell Mockito what to Mock
			Mockito.when(offersDAOImpl.allOffers()).thenReturn(myOffers);
			
			// Create a list object to make the call to the allOffers service method
			List<Offers> returnedOffers = offersService.carOffers(carId);
			
			// Test the size of the list
			Assertions.assertEquals(myOffers.size(), returnedOffers.size());
			
		}catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Tests the statusUpdate when the offer is Declined
	@Test
	void testStatusUpdateWhenDeclined() {
		
		// Create dummy variables to pass through the test
		int offerId = 1010;
		String status = "Declined";
		
		try {
			
			// Tell Mockito what to Mock
			Mockito.when(offersDAOImpl.statusUpdate(offerId, status)).thenReturn(1);
			
			// Create an int variable to store the statusUpdate service method
			int c = offersService.statusUpdate(offerId, status);
			
			// Test if the update is successful
			Assertions.assertEquals(1, c);
			
		}catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// Tests the statusUpdate when employee gives an invalid status
	@Test
	void testStatusUpdateWhenInvalid() {
		
		// Create dummy values to pass through the test
		int offerId = 1010;
		String status = "deklined";
		
		// Used to test if an expected exception was thrown
		Exception exception = Assertions.assertThrows(BusinessException.class, () -> {
			offersService.statusUpdate(offerId, status);
		});
		
		// Messages that will be checked 
		String expectedMessage = "Invalid status input.";
		String actualMessage = exception.getMessage();
		
		// Testing the message
		Assertions.assertTrue(actualMessage.contains(expectedMessage));
		
	}

}
