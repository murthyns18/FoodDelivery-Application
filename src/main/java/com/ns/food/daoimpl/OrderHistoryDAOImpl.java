package com.ns.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ns.food.connector.Connect;
import com.ns.food.dao.OrderHistoryDAO;
import com.ns.food.model.OrderHistory;

public class OrderHistoryDAOImpl implements OrderHistoryDAO
{
	ArrayList<OrderHistory> ohList = new ArrayList<>();
	
	private static String INSERT = "insert into orderhistory(orderId, userId, totalAmount, status) values (?,?,?,?)";
	private static String FETCHALL = "select * from orderhistory";
	private static String FETCHONE = "select * from orderhistory where orderHistoryId = ?";
	private static String UPDATE = "update orderhistory set status = ? where orderHistoryId = ?";
	private static String DELETE = "delete from orderhistory where orderHistoryId = ?";
	private static Connection con;
	
	static
	{
		try
		{
			con = Connect.connect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private PreparedStatement pstmt;

	private Statement stmt;

	private ResultSet resultSet;

	private OrderHistory orhistory;
	@Override
	public int insert(OrderHistory oh)
	{
		try
		{
			pstmt=con.prepareStatement(INSERT);
			pstmt.setInt(1, oh.getOrderId());
			pstmt.setInt(2, oh.getUserId());
			pstmt.setInt(3, oh.getTotalAmount());
			pstmt.setString(4, oh.getStatus());

			return pstmt.executeUpdate();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<OrderHistory> fetchAll() 
	{
		try 
		{
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(FETCHALL);
			ohList =extractOrderHistoryListFromResultSet(resultSet);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ohList;
	}
	ArrayList<OrderHistory> extractOrderHistoryListFromResultSet(ResultSet resultSet)
	{
		try {
			while(resultSet.next())
			{
				ohList.add(
						new OrderHistory(
								resultSet.getInt("orderId"),	
								resultSet.getInt("userId"),
								resultSet.getInt("totalAmount"),
								resultSet.getString("status")
								)
						);

			} 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ohList;
	}

	@Override
	public OrderHistory fetchOne(int orderHistoryId)
	{
		try 
		{
			pstmt = con.prepareStatement(FETCHONE);
			pstmt.setInt(1, orderHistoryId);
			resultSet = pstmt.executeQuery();
			ohList = extractOrderHistoryListFromResultSet(resultSet);
			
			orhistory = ohList.get(0);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return orhistory;
	}

	@Override
	public int update(int orderHistoryId, String status)
	{
		try
		{
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, status);
			pstmt.setInt(2, orderHistoryId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int orderHistoryId)
	{
		try
		{
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, orderHistoryId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

}
