package com.ns.food.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.ns.food.dao.OrderItemsDAO;
import com.ns.food.daoimpl.OrderItemsDAOImpl;
import com.ns.food.model.OrderItems;

public class LaunchOrderItems 
{
	public static void main(String[] args) 
	{
		OrderItemsDAO orderItemsList = new OrderItemsDAOImpl();
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Select your choice");
		System.out.println("1 : Insert a  new order item \n"
				+ "2 : Fetch all order item details \n"
				+ "3 : Fetch individual order item \n"
				+ "4 : Update the order item quantity \n"
				+ "5 : Delete the order item");
		int choice = sc.nextInt();
		
		switch (choice)
        {
            case 1:
                System.out.println("Enter order ID:");
                int orderId = sc.nextInt();
                System.out.println("Enter menu ID");
                int menuId = sc.nextInt();
                System.out.println("Enter quantity");
                int quantity = sc.nextInt();
                System.out.println("Enter item total");
                int itemTotal = sc.nextInt();

               int res =  orderItemsList.insert(new OrderItems(orderId, menuId, quantity, itemTotal));
               System.out.println(res == 1 ? "Order item inserted successfully" : "Failure to insert order item");
                break;

            case 2:
                ArrayList<OrderItems> oiList = orderItemsList.fetchAll();
                for (OrderItems ori : oiList) 
                {
                    System.out.println(ori);
                }
                break;

            case 3:
                System.out.println("Enter order item ID:");
                int oi = sc.nextInt();
                System.out.println(orderItemsList.fetchOne(oi));
                break;

            case 4:
                System.out.println("Enter order item ID:");
                int ord = sc.nextInt();
                System.out.println("Enter quantity");
                int q = sc.nextInt();

                System.out.println(orderItemsList.update(ord, q) == 1
                        ? " Order item quantity updated successfully"
                        : "Failed to update order item quantity ");
                break;

            case 5:
                System.out.println("Enter order item ID:");
                int deleteId = sc.nextInt();
                System.out.println(orderItemsList.delete(deleteId) == 1 ? "Order item deleted successfully" : "Failed to delete order item");
                break;

            default:
                System.out.println("Invalid choice");
        }
	}

}
