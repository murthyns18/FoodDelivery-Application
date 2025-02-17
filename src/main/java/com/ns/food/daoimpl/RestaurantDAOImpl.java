	package com.ns.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ns.food.connector.Connect;
import com.ns.food.dao.RestaurantDAO;
import com.ns.food.model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO 
{
    static final String INSERT = "insert into restaurant(name, cuisineType, deliveryTime, address, ratings, isActive, imagePath) values(?,?,?,?,?,?,?)";
    static final String FETCHALL = "select * from restaurant";
    static final String FETCHONE = "select * from restaurant where restaurantId = ?";
    static final String UPDATE = "update restaurant set address=? where restaurantId = ?";
    static final String DELETE = "delete from restaurant where restaurantId = ?";

    static Connection con;

    ArrayList<Restaurant> restaurantList = new ArrayList<>();

    static 
    {
        try 
        {
            con = Connect.connect();
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            e.printStackTrace();
        }
    }

    private PreparedStatement pstmt;
    private ResultSet resultSet;
    private Statement stmt;
    private Restaurant restaurant;

    @Override
    public int insert(Restaurant restaurant) 
    {
        try
        {
            pstmt = con.prepareStatement(INSERT);
            pstmt.setString(1, restaurant.getName());
            pstmt.setString(2, restaurant.getCuisineType());
            pstmt.setInt(3, restaurant.getDeliveryTime());
            pstmt.setString(4, restaurant.getAddress());
            pstmt.setFloat(5, restaurant.getRatings());
            pstmt.setBoolean(6, restaurant.isActive());
            pstmt.setString(7, restaurant.getImagePath());

            return pstmt.executeUpdate();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Restaurant> fetchAll() 
    {
        try 
        {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(FETCHALL);

            restaurantList = extractRestaurantListFromResultSet(resultSet);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return restaurantList;
    }

    @Override
    public Restaurant fetchOne(int restaurantId) 
    {
        try 
        {
            pstmt = con.prepareStatement(FETCHONE);
            pstmt.setInt(1, restaurantId);

            resultSet = pstmt.executeQuery();
            restaurantList = extractRestaurantListFromResultSet(resultSet);
            if(!restaurantList.isEmpty()) 
            {
                restaurant = restaurantList.get(0);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return restaurant;
    }

    private ArrayList<Restaurant> extractRestaurantListFromResultSet(ResultSet resultSet) 
    {
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        try
        {
            while (resultSet.next()) 
            {
                restaurantList.add(
                    new Restaurant(
                        resultSet.getInt("restaurantId"),
                        resultSet.getString("name"),
                        resultSet.getString("cuisineType"),
                        resultSet.getInt("deliveryTime"),
                        resultSet.getString("address"),
                        resultSet.getFloat("ratings"),
                        resultSet.getBoolean("isActive"),
                        resultSet.getString("imagePath")
                    )
                );
            }
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return restaurantList;
    }

    @Override
    public int update(int restaurantId, String address) 
    {
    	try 
		{
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(2, restaurantId);
			pstmt.setString(1, address);
			
			return pstmt.executeUpdate();
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
    }

    @Override
    public int delete(int restaurantId) 
    {
        try 
        {
            pstmt = con.prepareStatement(DELETE);
            pstmt.setInt(1, restaurantId);

            return pstmt.executeUpdate();
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

}
