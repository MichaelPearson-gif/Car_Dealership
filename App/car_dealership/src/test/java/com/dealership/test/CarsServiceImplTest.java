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
		
		// Create a list of cars to be expected to returned
		List<Cars> myCars = new ArrayList<>(Arrays.asList(new Cars(200, "Owned", "off", 2006, "Acura MDX", "White", 5000.00, "testGuy"),
				new Cars(201, "Owned", "off", 2010, "Honda Civic", "Red", 7000.00, "testGuy")));
		
		try {
			// Tell Mockito what to mock for this test
			Mockito.when(carsDAOImpl.customerCars("testGuy")).thenReturn(myCars);
			
			// Create a list object of the carsService.customerCars test
			List<Cars> returnedCars = carsService.customerCars("testGuy");
			
			// Loop throught the returnedCars list to test and make sure the username matches
			for(Cars car : returnedCars) {
				Assertions.assertEquals("testGuy", car.getUsername());
			}
			
		}catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	void testAllCarsOnLot() {
		
		// Create a list of cars to be expected to returned
		List<Cars> allCars = new ArrayList<>(Arrays.asList(new Cars(200, "None", "on", 2006, "Acura MDX", "White", 5000.00, null),
				new Cars(201, "None", "on", 2010, "Honda Civic", "Red", 7000.00, null),
				new Cars(199, "None", "on", 2000, "Toyota Carolla", "Blue", 2000.00, null),
				new Cars(210, "None", "on", 2019, "Honda Accord", "Black", 23720.00, null)));
		
		try {
			
			// Tell Mockito what to Mock
			Mockito.when(carsDAOImpl.allCarsOnLot()).thenReturn(allCars);
			
			// Create a list object of the carsService.allCars test
			List<Cars> returnedLotCars = carsService.allCarsOnLot();
			
			//Loop throught the returnedLotCars list to test and make sure all cars are on the lot
			for(Cars car : returnedLotCars) {
				Assertions.assertEquals("on", car.getLot());
			}
			
		}catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
