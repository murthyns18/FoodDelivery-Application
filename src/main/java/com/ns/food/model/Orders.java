package com.ns.food.model;

public class Orders 
{
	private int ordersId;
	private int userId;
	private int restaurantId;
	private double totalAmount;
	private String status;
	private String paymentMode;

	public Orders() 
	{
		super();
	}

	public Orders(int userId, int restaurantId, int totalAmount, String status, String paymentMode) 
	{
		super();
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMode = paymentMode;
	}

	public int getOrdersId() 
	{
		return ordersId;
	}

	public void setOrdersId(int ordersId) 
	{
		this.ordersId = ordersId;
	}

	public int getUserId() 
	{
		return userId;
	}

	public void setUserId(int userId) 
	{
		this.userId = userId;
	}

	public int getRestaurantId() 
	{
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) 
	{
		this.restaurantId = restaurantId;
	}

	public double getTotalAmount() 
	{
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) 
	{
		this.totalAmount = totalAmount;
	}

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getPaymentMode() 
	{
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) 
	{
		this.paymentMode = paymentMode;
	}

	@Override
	public String toString() 
	{
		return ordersId +"  "+userId+"  "+restaurantId+"  "+totalAmount+"  " + status + "  " + paymentMode;
	}

}
