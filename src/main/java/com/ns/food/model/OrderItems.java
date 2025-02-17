package com.ns.food.model;

public class OrderItems 
{
	private int orderItemsId;
	private int orderId;
	private int menuId;
	private int quantity;
	private int itemTotal;
	
	public OrderItems() 
	{
		super();
	}


	public OrderItems(int orderId, int menuId, int quantity, int itemTotal) 
	{
		super();
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.itemTotal = itemTotal;
	}


	public int getOrderItemsId() 
	{
		return orderItemsId;
	}


	public void setOrderItemsId(int orderItemsId) 
	{
		this.orderItemsId = orderItemsId;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) 
	{
		this.orderId = orderId;
	}


	public int getMenuId() 
	{
		return menuId;
	}


	public void setMenuId(int menuId) 
	{
		this.menuId = menuId;
	}


	public int getQuantity() 
	{
		return quantity;
	}


	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}


	public int getItemTotal() 
	{
		return itemTotal;
	}


	public void setItemTotal(int itemTotal) 
	{
		this.itemTotal = itemTotal;
	}


	@Override
	public String toString() 
	{
		return orderItemsId + "  " + orderId + "  " + menuId
				+ "  " + quantity + "  " + itemTotal;
	}
	
}
