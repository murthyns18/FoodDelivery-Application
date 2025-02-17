package com.ns.food.controller;

import java.io.IOException;
import java.util.List;

import com.ns.food.dao.RestaurantDAO;
import com.ns.food.daoimpl.RestaurantDAOImpl;
import com.ns.food.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/GetRestaurants")
public class GetAllRestaurantsServlet extends HttpServlet 
{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        RestaurantDAO dao = new RestaurantDAOImpl();
        List<Restaurant> restaurantList = dao.fetchAll();
        
        HttpSession session = req.getSession();
        session.setAttribute("RestaurantList", restaurantList);
        
        resp.sendRedirect("Home.jsp");
    }
}
