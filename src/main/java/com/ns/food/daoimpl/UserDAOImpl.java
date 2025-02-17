package com.ns.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ns.food.connector.Connect;
import com.ns.food.dao.UserDAO;
import com.ns.food.model.User;

public class UserDAOImpl implements UserDAO
{
	static final String INSERT = "insert into user(userName, password, email, address) values(?,?,?,?)";
	static final String FETCHALL = "select * from user";
	static final String FETCHONE = "select * from user where userId = ?";
	static final String  UPDATE = "update user set address = ? where userId = ?";
	static final String DELETE = "delete from user where userId = ?";
	
	private static final String fetchByEmail = "SELECT * FROM user WHERE email = ?";
	private static final String URL = "jdbc:mysql://localhost:3306/project";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "murthy";
	static Connection con;

	ArrayList<User> userList = new ArrayList<>();
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
	private ResultSet resultSet;
	private Statement stmt;
	private User u;

	@Override
	public int insert(User userId) 
	{
		try
		{
			pstmt=con.prepareStatement(INSERT);
			pstmt.setString(1, userId.getUserName());
			pstmt.setString(2, userId.getPassword());
			pstmt.setString(3, userId.getEmail());
			pstmt.setString(4, userId.getAddress());

			return pstmt.executeUpdate();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<User> fetchAll() 
	{
		try 
		{
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(FETCHALL);

			userList = extractUserListFromResultSet(resultSet);

		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public User fetchOne(int userId) 
	{
		try 
		{
			pstmt = con.prepareStatement(FETCHONE);
			pstmt.setInt(1, userId);

			resultSet = pstmt.executeQuery();
			userList = extractUserListFromResultSet(resultSet);
			u = userList.get(0);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return u;
	}

	ArrayList<User> extractUserListFromResultSet(ResultSet resultSet)
	{
		try {
			while(resultSet.next())
			{
				userList.add(
						new User(
								resultSet.getString("userName"),
								resultSet.getString("password"),
								resultSet.getString("email"),
								resultSet.getString("address")
								)
						);

			} 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public int update(int userId, String address) 
	{
		try 
		{
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(2, userId);
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
	public int delete(int userId) 
	{
		try 
		{
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, userId);
			return pstmt.executeUpdate();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public User fetchByEmail(String email) 
	{
	    User user = null;
	    try 
	    {
	        pstmt = con.prepareStatement(fetchByEmail);
	        pstmt.setString(1, email);
	        resultSet = pstmt.executeQuery();

	        if (resultSet.next()) 
	        {
	            user = new User();
	            user.setUserId(resultSet.getInt("userId"));
	            user.setUserName(resultSet.getString("userName"));
	            user.setPassword(resultSet.getString("password"));
	            user.setEmail(resultSet.getString("email"));
	            user.setAddress(resultSet.getString("address"));
	        }
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }
	    return user;
	}

}
