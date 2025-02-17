package com.ns.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ns.food.connector.Connect;
import com.ns.food.dao.OrdersDAO;
import com.ns.food.model.Orders;

public class OrdersDAOImpl implements OrdersDAO 
{
    static final String INSERT = "insert into orders(userId, restaurantId, totalAmount, status, paymentMode) values(?,?,?,?,?)";
    static final String FETCHALL = "select * from orders";
    static final String FETCHONE = "select * from orders where orderId = ?";
    static final String UPDATE = "update orders set status = ? where orderId = ?";
    static final String DELETE = "delete from orders where orderId = ?";
    private static Connection con = null;
    ArrayList<Orders> ordersList = new ArrayList<>();

    static
    {
        try
        {
            con = Connect.connect();
        } 
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;
    private Orders o;

    @Override
    public int insert(Orders o)
    {
        try 
        {
            pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, o.getUserId());
            pstmt.setInt(2, o.getRestaurantId());
            pstmt.setInt(3, (int) o.getTotalAmount());
            pstmt.setString(4, o.getStatus());
            pstmt.setString(5, o.getPaymentMode());

            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) 
            {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) 
                {
                    if (generatedKeys.next()) {
                        o.setOrdersId(generatedKeys.getInt(1));
                    }
                }
            }
            return affectedRows;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Orders> fetchAll() 
    {
        try
        {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(FETCHALL);
            ordersList = extractOrdersListFromResultSet(resultSet);
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return ordersList;
    }

    private ArrayList<Orders> extractOrdersListFromResultSet(ResultSet resultSet) 
    {
        try
        {
            while (resultSet.next()) 
            {
                ordersList.add(new Orders(
                        resultSet.getInt("userId"),
                        resultSet.getInt("restaurantId"),
                        resultSet.getInt("totalAmount"),
                        resultSet.getString("status"),
                        resultSet.getString("paymentMode"))
                );
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return ordersList;
    }

    @Override
    public Orders fetchOne(int orderId) 
    {
        try 
        {
            pstmt = con.prepareStatement(FETCHONE);
            pstmt.setInt(1, orderId);
            resultSet = pstmt.executeQuery();
            ordersList = extractOrdersListFromResultSet(resultSet);
            o = ordersList.get(0);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public int update(int orderId, String status)
    {
        try
        {
            pstmt = con.prepareStatement(UPDATE);
            pstmt.setString(1, status);
            pstmt.setInt(2, orderId);
            return pstmt.executeUpdate();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int orderId) 
    {
        try 
        {
            pstmt = con.prepareStatement(DELETE);
            pstmt.setInt(1, orderId);
            return pstmt.executeUpdate();
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}
