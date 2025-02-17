package com.ns.food.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.ns.food.dao.OrderHistoryDAO;
import com.ns.food.daoimpl.OrderHistoryDAOImpl;
import com.ns.food.model.OrderHistory;

public class LaunchOrderHistoy 
{
	public static void main(String[] args) 
	{
		OrderHistoryDAO orderHistory = new OrderHistoryDAOImpl();
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Select your choice:");
	    System.out.println("1: Insert a order history");
	    System.out.println("2: Fetch all order history");
	    System.out.println("3: Fetch individual order history");
	    System.out.println("4: Update order history status");
	    System.out.println("5: Delete a order history");

	    int choice = sc.nextInt();
	    
	    switch(choice)
		{
		case 1 : 
			System.out.println("Enter order id");
			int orderId=sc.nextInt();
			System.out.println("Enter user id");
			int userId = sc.nextInt();
			System.out.println("Enter total amount");
			int totalAmount = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter status");
			String status=sc.nextLine();

			int x = orderHistory.insert(new OrderHistory(orderId,userId,totalAmount,status));

			System.out.println(x==1 ? "Inserted successfully" : "Failure to insert");
			break;

		case 2 :
			ArrayList<OrderHistory> ohList = orderHistory.fetchAll();
			for(OrderHistory oh : ohList)
			{
				System.out.println(oh);
			}
			break;

		case 3 :
			System.out.println("Enter order history id");
			int id1 = sc.nextInt();
			OrderHistoryDAO oh = new OrderHistoryDAOImpl();
			System.out.println(oh.fetchOne(id1));
			break;

		case 4 :
			System.out.println("Enter order history id");
			int id2 = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter status");
			String stat = sc.nextLine();
			System.out.println(orderHistory.update(id2, stat)==1 ? "Updated successfully" : "Failure to update") ;
			break;

		case 5 :
			System.out.println("Enter order history id");
			int id3=sc.nextInt();
			System.out.println(orderHistory.delete(id3)==1 ? "Deleted successfully" : "Failure to delete");
			break;

		default :
			System.out.println("Invalid choice");

		}
	    
	}
}
