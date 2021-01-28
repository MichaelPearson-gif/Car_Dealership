package com.dealership.model;

public class Cars {

	private int carId;
	private String owner;
	private String lot;
	private int make;
	private String model;
	private String color;
	private double price;
	private String username;
	
	public Cars() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cars(int carId, String owner, String lot, int make, String model, String color, double price,
			String username) {
		super();
		this.carId = carId;
		this.owner = owner;
		this.lot = lot;
		this.make = make;
		this.model = model;
		this.color = color;
		this.price = price;
		this.username = username;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public int getMake() {
		return make;
	}

	public void setMake(int make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Cars [carId=" + carId + ", owner=" + owner + ", lot=" + lot + ", make=" + make + ", model=" + model
				+ ", color=" + color + ", price=" + price + ", username=" + username + "]";
	}
	
}
