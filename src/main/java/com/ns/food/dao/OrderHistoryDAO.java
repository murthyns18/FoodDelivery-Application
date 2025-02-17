package com.ns.food.dao;

import java.util.ArrayList;

import com.ns.food.model.OrderHistory;

public interface OrderHistoryDAO 
{
	int insert(OrderHistory oh);
	ArrayList<OrderHistory> fetchAll();
	OrderHistory fetchOne(int orderHistoryId);
	int update(int orderHistoryId, String status);
	int delete(int orderHistoryId);

}
