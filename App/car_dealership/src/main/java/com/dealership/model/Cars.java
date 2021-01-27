package com.dealership.model;

public class Cars {

	private int carId;
	private String owner;
	private String lot;
	private double price;
	
	public Cars() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cars(int carId, String owner, String lot, double price) {
		super();
		this.carId = carId;
		this.owner = owner;
		this.lot = lot;
		this.price = price;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Cars [carId=" + carId + ", owner=" + owner + ", lot=" + lot + ", price=" + price + "]";
	}
	
}
