package com.ns.food.dao;

import java.util.ArrayList;

import com.ns.food.model.OrderItems;

public interface OrderItemsDAO 
{
	int insert(OrderItems oi);
	ArrayList<OrderItems> fetchAll();
	OrderItems fetchOne(int orderItemId);
	int update(int orderItemId, int quantity);
	int delete(int orderItemId);
}
