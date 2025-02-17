package com.ns.food.controller;
import com.ns.food.model.Menu;

import java.io.IOException;
import java.util.List;

import com.ns.food.dao.MenuDAO;
import com.ns.food.daoimpl.MenuDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet 
{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
        
        HttpSession session = req.getSession();
        session.setAttribute("restaurantId", restaurantId);   

        MenuDAO dao = new MenuDAOImpl();
        List<Menu> menuList = dao.fetchByRestaurantId(restaurantId);

        session.setAttribute("MenuList", menuList);

        resp.sendRedirect("Menu.jsp");
    }
}
