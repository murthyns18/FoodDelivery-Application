package com.ns.food.dao;

import java.util.ArrayList;

import com.ns.food.model.User;

public interface UserDAO 
{
	int insert(User u);
	ArrayList<User> fetchAll();
	User fetchOne(int userId);
	int update(int userId, String address);
	int delete(int userId);
	User fetchByEmail(String email);
}
