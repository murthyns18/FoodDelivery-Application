package com.ns.food.controller;

import java.io.IOException;

import com.ns.food.dao.UserDAO;
import com.ns.food.daoimpl.UserDAOImpl;
import com.ns.food.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet
{
    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        HttpServletRequest httpReq = (HttpServletRequest) req;
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserDAO dao = new UserDAOImpl();

        try 
        {
            User user = dao.fetchByEmail(email);

            if (user != null && password.equals(user.getPassword())) 
            {
                HttpSession session = httpReq.getSession();
                session.setAttribute("loggedInUser", user);
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("username", user.getUserName());


                httpResp.sendRedirect("GetRestaurants");
            } 
            else 
            {
                if (user == null) 
                {
                    httpResp.sendRedirect("InvalidUser.jsp");
                } 
                else
                {
                    httpResp.sendRedirect("InvalidPassword.jsp");
                }
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
