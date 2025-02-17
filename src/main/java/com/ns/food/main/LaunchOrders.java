package com.ns.food.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.ns.food.dao.OrdersDAO;
import com.ns.food.daoimpl.OrdersDAOImpl;
import com.ns.food.model.Orders;


public class LaunchOrders 
{

	public static void main(String[] args) 
	{
		OrdersDAO orders = new OrdersDAOImpl();		
		Scanner sc = new Scanner(System.in);
		System.out.println("Select your choice");
		System.out.println("1 : Insert an order \n"
				+ "2 : Fetch all orders \n"
				+ "3 : Fetch individual order \n"
				+ "4 : Update an order \n"
				+ "5 : Delete an order");
		int option = sc.nextInt();
		sc.nextLine();

		switch(option)
		{
		case 1 :
			System.out.println("Enter user Id");
			int userId = sc.nextInt();
			System.out.println("Enter restaurant Id");
			int restaurantId = sc.nextInt();
			System.out.println("Enter total Amount");
			int totalAmount = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter status");
			String status = sc.nextLine();
			System.out.println("Enter payment mode");
			String paymentMode = sc.nextLine();

			int x = orders.insert(new Orders(userId, restaurantId, totalAmount, status, paymentMode));
			System.out.println(x == 1 ? "Order inserted successfully " : "Failed to insert an order");
			break;

		case 2 :
			ArrayList<Orders> ordersList = orders.fetchAll();
			for(Orders or : ordersList)
			{
				System.out.println(or);
			}
			break;

		case 3 :
			OrdersDAO order = new OrdersDAOImpl();
			System.out.println("Enter id");
			int orderId1 = sc.nextInt();
			System.out.println(order.fetchOne(orderId1));
			break;

		case 4 :
			System.out.println("Enter order id");
			int oid = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter status");
			String status1 = sc.nextLine();
			int c = orders.update(oid, status1);  
			System.out.println(c==1 ? "Order status updated successfully": "Failure to update status order" );
			break;
			
		case 5 :
			System.out.println("Enter order id");
			int id2 = sc.nextInt();
			System.out.println(orders.delete(id2) == 1 ? "Order deleted successfully" : "Failure to delete an order");
			break;
			
		case 6 :
			System.out.println("Invalid choice");
			
		}
	}

}
