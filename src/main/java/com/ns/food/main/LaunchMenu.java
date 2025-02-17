package com.ns.food.main;

import java.awt.MenuItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ns.food.dao.MenuDAO;
import com.ns.food.daoimpl.MenuDAOImpl;
import com.ns.food.model.Menu;

public class LaunchMenu 
{

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        MenuDAO menuDAO = new MenuDAOImpl();

        System.out.println("Select your choice:");
        System.out.println("1: Insert a new menu item");
        System.out.println("2: Fetch all menu items");
        System.out.println("3: Fetch individual menu item details");
        System.out.println("4: Update menu item price");
        System.out.println("5: Delete a menu item");
        System.out.println("6: Fetch by restaurant id");

        int choice = sc.nextInt();

        switch (choice)
        {
            case 1:
                System.out.println("Enter restaurant ID:");
                int restaurantId = sc.nextInt();
                sc.nextLine(); // Consume newline
                System.out.println("Enter menu name:");
                String name = sc.nextLine();
                System.out.println("Enter description:");
                String description = sc.nextLine();
                System.out.println("Enter price:");
                int price = sc.nextInt();
                System.out.println("Is the menu item available?");
                boolean isAvailable = sc.nextBoolean();
                sc.nextLine(); // Consume newline
                System.out.println("Enter image path:");
                String imagePath = sc.nextLine();

                Menu menu = new Menu(restaurantId, name, description, price, isAvailable, imagePath);
                System.out.println(menuDAO.insert(menu) == 1 ? "Inserted successfully" : "Failed to insert");
                break;

            case 2:
                ArrayList<Menu> menuList = menuDAO.fetchAll();
                for (Menu menuItem : menuList) 
                {
                    System.out.println(menuItem);
                }
                break;

            case 3:
                System.out.println("Enter menu ID:");
                int fetchId = sc.nextInt();
                Menu fetchedMenu = menuDAO.fetchOne(fetchId);
                System.out.println(fetchedMenu != null ? fetchedMenu : "Menu item not found");
                break;

            case 4:
                System.out.println("Enter menu ID:");
                int updateId = sc.nextInt();
                System.out.println("Enter new price:");
                int newPrice = sc.nextInt();

                System.out.println(menuDAO.update(updateId, newPrice) == 1
                        ? "Updated successfully"
                        : "Failed to update");
                break;

            case 5:
                System.out.println("Enter menu ID:");
                int deleteId = sc.nextInt();
                System.out.println(menuDAO.delete(deleteId) == 1 ? "Deleted successfully" : "Failed to delete");
                break;
                
            case 6:
            	System.out.println("Restaurant ID:");
            	int restaurant = sc.nextInt();
            	 List<Menu> menulist =  menuDAO.fetchByRestaurantId(restaurant);
            	 for(Menu m : menulist)
            	 {
            		 System.out.println(m);
            	 }
            	break;

            default:
                System.out.println("Invalid choice");
        }

        sc.close();
    }
}
