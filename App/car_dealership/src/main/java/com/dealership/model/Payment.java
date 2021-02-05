package com.dealership.model;

import java.util.Date;

public class Payment {

	private int paymentId;
	private int carId;
	private double amount;
	private double totalPayment;
	private double monthlyPayment;
	private int remainingPayments;
	private Date date;

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(int paymentId, int carId, double amount, double totalPayment, double monthlyPayment,
			int remainingPayments, Date date) {
		super();
		this.paymentId = paymentId;
		this.carId = carId;
		this.amount = amount;
		this.totalPayment = totalPayment;
		this.monthlyPayment = monthlyPayment;
		this.remainingPayments = remainingPayments;
		this.date = date;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public int getRemainingPayments() {
		return remainingPayments;
	}

	public void setRemainingPayments(int remainingPayments) {
		this.remainingPayments = remainingPayments;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + carId;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		temp = Double.doubleToLongBits(monthlyPayment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + paymentId;
		result = prime * result + remainingPayments;
		temp = Double.doubleToLongBits(totalPayment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Payment other = (Payment) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (carId != other.carId)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Double.doubleToLongBits(monthlyPayment) != Double.doubleToLongBits(other.monthlyPayment))
			return false;
		if (paymentId != other.paymentId)
			return false;
		if (remainingPayments != other.remainingPayments)
			return false;
		if (Double.doubleToLongBits(totalPayment) != Double.doubleToLongBits(other.totalPayment))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", carId=" + carId + ", amount=" + amount + ", totalPayment="
				+ totalPayment + ", monthlyPayment=" + monthlyPayment + ", remainingPayments=" + remainingPayments
				+ ", date=" + date + "]";
	}

}
