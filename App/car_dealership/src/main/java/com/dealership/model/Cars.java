package com.dealership.model;

public class Cars {

	private int carId;
	private String ownerStatus;
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

	public Cars(int carId, String ownerStatus, String lot, int make, String model, String color, double price,
			String username) {
		super();
		this.carId = carId;
		this.ownerStatus = ownerStatus;
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

	public String getOwnerStatus() {
		return ownerStatus;
	}

	public void setOwnerStatus(String ownerStatus) {
		this.ownerStatus = ownerStatus;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carId;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((lot == null) ? 0 : lot.hashCode());
		result = prime * result + make;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((ownerStatus == null) ? 0 : ownerStatus.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cars other = (Cars) obj;
		if (carId != other.carId)
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (lot == null) {
			if (other.lot != null)
				return false;
		} else if (!lot.equals(other.lot))
			return false;
		if (make != other.make)
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (ownerStatus == null) {
			if (other.ownerStatus != null)
				return false;
		} else if (!ownerStatus.equals(other.ownerStatus))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cars [carId=" + carId + ", ownerStatus=" + ownerStatus + ", lot=" + lot + ", make=" + make + ", model="
				+ model + ", color=" + color + ", price=" + price + ", username=" + username + "]";
	}
	
}
