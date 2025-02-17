package com.ns.food.dao;

import java.util.ArrayList;

import com.ns.food.model.Restaurant;

public interface RestaurantDAO 
{
	int insert(Restaurant r);
	ArrayList<Restaurant> fetchAll();
	Restaurant fetchOne(int restaurantId);
	int update(int restaurantId, String address);
	int delete(int restaurantId);

}
