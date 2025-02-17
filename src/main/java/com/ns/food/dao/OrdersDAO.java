package com.ns.food.dao;

import java.util.ArrayList;

import com.ns.food.model.Orders;

public interface OrdersDAO 
{
	int insert(Orders o);
	ArrayList<Orders> fetchAll();
	Orders fetchOne(int orderId);
	int update(int orderId, String status);
	int delete(int orderId);
	
}
