package com.ns.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ns.food.connector.Connect;
import com.ns.food.dao.OrderItemsDAO;
import com.ns.food.model.OrderItems;

public class OrderItemsDAOImpl implements OrderItemsDAO
{

	ArrayList<OrderItems> orderItemsList = new ArrayList<>();
	static final String INSERT = "insert into orderitems(orderId, menuId, quantity, itemTotal)values (?,?,?,?)";
	static final String FETCHALL = "select *from orderitems";
	static final String FETCHONE = "select *from orderitems where orderItemId = ?";
	static final String UPDATE = "update orderitems set quantity = ? where orderItemId = ?";
	static final String DELTE = "delete from orderitems where orderItemId = ?";
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
	private ResultSet resultSet ;
	private ArrayList<OrderItems> orderItems;
	private OrderItems orderis;
	@Override
	public int insert(OrderItems oi) 
	{
		try 
		{
			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, oi.getOrderId());
			pstmt.setInt(2, oi.getMenuId());
			pstmt.setInt(3, oi.getQuantity());
			pstmt.setInt(4, oi.getItemTotal());

			return pstmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<OrderItems> fetchAll() 
	{
		try
		{
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(FETCHALL);
			orderItems = extractOrderItemsListFromResultSet(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderItems;
	}

	ArrayList<OrderItems> extractOrderItemsListFromResultSet(ResultSet resultSet)
	{
		try {
			while(resultSet.next())
			{
				orderItemsList.add(
						new OrderItems(
								resultSet.getInt("orderId"),
								resultSet.getInt("menuId"),
								resultSet.getInt("quantity"),
								resultSet.getInt("itemTotal")
								)
						);

			} 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return orderItemsList;
	}

	@Override
	public OrderItems fetchOne(int orderItemId) 
	{
		try 
		{
			pstmt = con.prepareStatement(FETCHONE);
			pstmt.setInt(1, orderItemId);
			resultSet = pstmt.executeQuery();
			
			orderItemsList = extractOrderItemsListFromResultSet(resultSet);
			orderis = orderItemsList.get(0);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return orderis;
	}

	@Override
	public int update(int orderItemId, int quantity) 
	{
		try
		{
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, orderItemId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int orderItemId) 
	{
		try
		{
			pstmt = con.prepareStatement(DELTE);
			pstmt.setInt(1, orderItemId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

}
