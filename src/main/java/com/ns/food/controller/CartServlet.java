package com.ns.food.controller;

import java.io.IOException;
import com.ns.food.dao.MenuDAO;
import com.ns.food.daoimpl.MenuDAOImpl;
import com.ns.food.model.Cart;
import com.ns.food.model.CartItem;
import com.ns.food.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");
        
        if (cart == null)
        {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");

        switch (action) 
        {
            case "add":
                addItemToCart(request, cart);
                break;
            case "update":
                updateCartItem(request, cart);
                break;
            case "remove":
                removeItemFromCart(request, cart);
                break;
            case "addMore":
                addMoreToCart(request, cart);
                break;
            default:
                break;
        }

        session.setAttribute("cart", cart);

        if ("addMore".equals(action)) 
        {
            response.sendRedirect("Menu.jsp");
        } 
        else
        {
            response.sendRedirect("Cart.jsp");   
        }
    }

    private void addItemToCart(HttpServletRequest request, Cart cart) 
    {
        try
        {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            MenuDAO menuDAO = new MenuDAOImpl();
            Menu menuItem = menuDAO.fetchOne(itemId);

            if (menuItem != null)
            {
                // Check if the item already exists in the cart
                CartItem existingItem = cart.getItems().get(itemId);
                if (existingItem != null)
                {
                    // If the item exists, update the quantity
                    cart.updateItemQuantity(itemId, existingItem.getQuantity() + quantity);
                } 
                else
                {
                    // If the item does not exist in the cart, add it
                    CartItem item = new CartItem(
                            menuItem.getMenuId(),
                            menuItem.getRestaurantId(),
                            menuItem.getName(),
                            menuItem.getPrice(),
                            quantity
                    );
                    cart.addItem(item);
                }
            }
        } 
        catch (NumberFormatException e) 
        {
            e.printStackTrace();
        }
    }

    private void updateCartItem(HttpServletRequest request, Cart cart)
    {
        try
        {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            cart.updateItemQuantity(itemId, quantity);   
        } 
        catch (NumberFormatException e) 
        {
            e.printStackTrace();
        }
    }

    private void removeItemFromCart(HttpServletRequest request, Cart cart)
    {
        try
        {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            cart.removeItem(itemId);
        } 
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
    }

    private void addMoreToCart(HttpServletRequest request, Cart cart) 
    {
        // Redirect to the menu to add more items
        
    }
}
