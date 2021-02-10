package com.dealership.test;

import static org.junit.jupiter.api.Assertions.fail;

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

	@Test
	void testCarOffers() {
		fail("Not yet implemented");
	}

	@Test
	void testStatusUpdate() {
		fail("Not yet implemented");
	}

}
