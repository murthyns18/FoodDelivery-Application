package com.ns.food.controller;

import java.io.IOException;

import com.ns.food.dao.OrderItemsDAO;
import com.ns.food.dao.OrdersDAO;
import com.ns.food.daoimpl.OrderItemsDAOImpl;
import com.ns.food.daoimpl.OrdersDAOImpl;
import com.ns.food.model.Cart;
import com.ns.food.model.CartItem;
import com.ns.food.model.OrderItems;
import com.ns.food.model.Orders;
import com.ns.food.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet
{

    private OrdersDAO ordersDAO;
    private OrderItemsDAO orderItemsDAO;

    @Override
    public void init()
    {
        try
        {
            ordersDAO = new OrdersDAOImpl();
            orderItemsDAO = new OrderItemsDAOImpl();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("loggedInUser");

        if (cart != null && !cart.getItems().isEmpty())
        {
        	if(user != null)
        	{
                String paymentMethod = request.getParameter("payment");
                String deliveryAddress = request.getParameter("address");

                Orders order = new Orders();
                order.setUserId(user.getUserId());
                order.setRestaurantId((int) session.getAttribute("restaurantId"));
                order.setTotalAmount(cart.getTotalAmount());
                order.setStatus("Pending");
                order.setPaymentMode(paymentMethod);

                int totalAmount = 0;
                for (CartItem item : cart.getItems().values()) 
                {
                    totalAmount += item.getPrice() * item.getQuantity();
                }
                
                order.setTotalAmount(totalAmount);
                ordersDAO.insert(order);

                int orderId = order.getOrdersId(); 

                for (CartItem item : cart.getItems().values())
                {
                    OrderItems orderItem = new OrderItems();
                    orderItem.setOrderId(orderId); 
                    orderItem.setMenuId(item.getItemId());
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setItemTotal((int) (item.getPrice() * item.getQuantity()));

                    orderItemsDAO.insert(orderItem);
                }

                session.setAttribute("order", order);
                session.removeAttribute("cart");

                response.sendRedirect("order_confirmation.jsp");
        	}
        	else
        	{
        		response.sendRedirect("Login.jsp");
        		
        	}


        } 
        else 
        {
            response.sendRedirect("Home.jsp");
        }
    }
}
