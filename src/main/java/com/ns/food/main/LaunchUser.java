package com.ns.food.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.ns.food.dao.UserDAO;
import com.ns.food.daoimpl.UserDAOImpl;
import com.ns.food.model.User;

public class LaunchUser 
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);

		UserDAO userdao = new UserDAOImpl();

		System.out.println("Select your choice");
		System.out.println("                   ");
		System.out.println("1 : Insert a  new user \n"
				+ "2 : Fetch all users details \n"
				+ "3 : Fetch individual user details \n"
				+ "4 : Update the user \n"
				+ "5 : Delete the user");

		int choice = sc.nextInt();
		sc.nextLine();

		switch(choice)
		{
		case 1 : 

			System.out.println("Enter name");
			String name = sc.nextLine();
			System.out.println("Enter password");
			String pass = sc.nextLine();
			System.out.println("Enter email");
			String email=sc.nextLine();
			System.out.println("Enter address");
			String address = sc.nextLine();

			int x = userdao.insert(new User(name,pass,email,address));

			System.out.println(x==1 ? "Inserted successfully" : "Failure to insert");
			break;

		case 2 :
			ArrayList<User> ulist = userdao.fetchAll();
			for(User uu : ulist)
			{
				System.out.println(uu);
			}
			break;

		case 3 :
			System.out.println("Enter id");
			int id1 = sc.nextInt();
			UserDAO udao = new UserDAOImpl();
			System.out.println(udao.fetchOne(id1));
			break;

		case 4 :
			System.out.println("Enter id");
			int id2 = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter address");
			String address1 = sc.nextLine();
			System.out.println(userdao.update(id2, address1)==1 ? "Updated successfully" : "Failure to update") ;
			break;

		case 5 :
			System.out.println("Enter the id");
			int id3=sc.nextInt();
			System.out.println(userdao.delete(id3)==1 ? "Deleted successfully" : "Failure to delete");
			break;

		default :
			System.out.println("Invalid choice");

		}

	}

}
