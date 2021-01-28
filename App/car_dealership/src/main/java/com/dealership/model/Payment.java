package com.dealership.model;

import java.util.Date;

public class Payment {

	private int paymentId;
	private int carId;
	private double amount;
	private double totalPayment;
	private double monthlyPayment;
	private double remainingPayments;
	private Date date;
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(int paymentId, int carId, double amount, double totalPayment, double monthlyPayment,
			double remainingPayments, Date date) {
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

	public double getRemainingPayments() {
		return remainingPayments;
	}

	public void setRemainingPayments(double remainingPayments) {
		this.remainingPayments = remainingPayments;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "PaymentHistory [paymentId=" + paymentId + ", carId=" + carId + ", amount=" + amount + ", totalPayment="
				+ totalPayment + ", monthlyPayment=" + monthlyPayment + ", remainingPayments=" + remainingPayments
				+ ", date=" + date + "]";
	}
	
}
