package com.dealership.test;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.dealership.dao.impl.CarsDAOImpl;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.Cars;
import com.dealership.service.CarsService;
import com.dealership.service.impl.CarsServiceImpl;

class CarsServiceImplTest {
	
	// Inject Mocks
	@InjectMocks
	private static CarsService carsService;
	
	// Mock the DAO implementation
	@Mock
	private CarsDAOImpl carsDAOImpl;

	// Setup the carsService to the implementation class
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		carsService = new CarsServiceImpl();
	}

	// Before each test, mock the DAO implementation
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	// Write all test cases
	@Test
	void testNewCar() {
		
		// Create a dummy car object to pass through the test
		Cars car = new Cars(200, "None", "on", 2006, "Acura MDX", "White", 5000.00, null);
		
		try {
			// Tell Mockito what to mock for this test
			Mockito.when(carsDAOImpl.newCar(car)).thenReturn(1);
			
			// Create an int value to store the newCar service method
			int c = carsService.newCar(car);
			
			// Use assertEquals to test if it works
			Assertions.assertEquals(1, c);
			
		}catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testCustomerCars() {
		fail("Not yet implemented");
	}

	@Test
	void testAllCarsOnLot() {
		fail("Not yet implemented");
	}

	@Test
	void testCarUpdateIntString() {
		fail("Not yet implemented");
	}

	@Test
	void testCarUpdateInt() {
		fail("Not yet implemented");
	}

}
