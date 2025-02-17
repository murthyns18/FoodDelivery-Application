package com.ns.food.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.ns.food.dao.RestaurantDAO;
import com.ns.food.daoimpl.RestaurantDAOImpl;
import com.ns.food.model.Restaurant;

public class LaunchRestaurant 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();

        System.out.println("Select your choice");
        System.out.println("                   ");
        System.out.println("1 : Insert a new restaurant \n"
                + "2 : Fetch all restaurant details \n"
                + "3 : Fetch individual restaurant details \n"
                + "4 : Update the restaurant address \n"
                + "5 : Delete the restaurant");

        int choice = sc.nextInt();

        switch (choice) 
        {
            case 1:
                sc.nextLine();
                System.out.println("Enter restaurant name:");
                String name = sc.nextLine();
                System.out.println("Enter cuisine type:");
                String cuisineType = sc.nextLine();
                System.out.println("Enter delivery time (in minutes):");
                int deliveryTime = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter address:");
                String address = sc.nextLine();
                System.out.println("Enter ratings:");
                float ratings = sc.nextFloat();
                System.out.println("Is the restaurant active? (true/false):");
                boolean isActive = sc.nextBoolean();
                sc.nextLine();
                System.out.println("Enter image path:");
                String imagePath = sc.nextLine();

                int insertResult = restaurantDAO.insert(new Restaurant(name, cuisineType, deliveryTime, address, ratings, isActive, imagePath));

                System.out.println(insertResult == 1 ? "Inserted successfully" : "Failed to insert");
                break;

            case 2:
                ArrayList<Restaurant> restaurantList = restaurantDAO.fetchAll();
                for (Restaurant restaurant : restaurantList) 
                {
                    System.out.println(restaurant);
                }
                break;

            case 3:
                System.out.println("Enter restaurant ID:");
                int fetchId = sc.nextInt();
                System.out.println(restaurantDAO.fetchOne(fetchId));
                break;

            case 4:
                System.out.println("Enter restaurant ID:");
                int updateId = sc.nextInt();
                sc.nextLine(); // Consume the newline
                System.out.println("Enter new address:");
                String newAddress = sc.nextLine();
                System.out.println(restaurantDAO.update(updateId, newAddress) == 1 
                    ? "Updated successfully" 
                    : "Failed to update");
                break;


            case 5:
                System.out.println("Enter restaurant ID:");
                int deleteId = sc.nextInt();
                System.out.println(restaurantDAO.delete(deleteId) == 1 ? "Deleted successfully" : "Failed to delete");
                break;

            default:
                System.out.println("Invalid choice");
        }

        sc.close();
    }
}
