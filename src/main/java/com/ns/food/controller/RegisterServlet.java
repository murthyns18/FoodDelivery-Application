package com.ns.food.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ns.food.connector.Connect;
import com.ns.food.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet 
{	
	String  fetchMail="select *from user where email=?";
	
	private static Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	
	static 
	{
		try
		{
			con=Connect.connect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmpassword = req.getParameter("confirmpassword");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		
		if(password.equals(confirmpassword))
		{
			try 
			{
				pstmt = con.prepareStatement(fetchMail);
				pstmt.setString(1, email);
				res = pstmt.executeQuery();
				if(res.next())
				{
					resp.sendRedirect("AlreadyRegistered.jsp");
				}
				else
				{
					User u = new User(username, password, email, address);
					String insert = "insert into user(username, password, email, address)values(?,?,?,?)";
					pstmt = con.prepareStatement(insert);
					pstmt.setString(1, u.getUserName());
					pstmt.setString(2, u.getPassword());
					pstmt.setString(3, u.getEmail());
					pstmt.setString(4, u.getAddress());
					
					int x = pstmt.executeUpdate();
					if(x==1)
					{
						resp.sendRedirect("Success.jsp");
					}
					else
					{
						resp.sendRedirect("Failure.jsp");
					}
				}
			
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		}
		else
		{
			resp.sendRedirect("PasswordMissmatch.jsp");
		}
		
	}
}
