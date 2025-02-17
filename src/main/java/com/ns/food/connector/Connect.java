package com.ns.food.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect 
{
	static Connection con = null;
	static String url = "jdbc:mysql://localhost:3306/project";
	static String un = "root";
	static String pwd = "murthy";

	public static Connection connect() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, un, pwd);
		return con;
	}
}
