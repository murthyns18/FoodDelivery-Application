package com.ns.food.dao;

import java.util.ArrayList;
import java.util.List;

import com.ns.food.model.Menu;


public interface MenuDAO 
{
	int insert(Menu m);
	ArrayList<Menu> fetchAll();
	Menu fetchOne(int userId);
	int update(int menuId, int price);
	int delete(int menuId);
	List<Menu> fetchByRestaurantId(int restaurantId);
	Menu getMenu(int itemId);
}
