<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ns.food.model.Menu" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Items</title>
    <link rel="stylesheet" href="menu.css">
</head>
<body>
<header>
    <h1 class="main-title">Menu Items</h1>
</header>
<div class="card-container">
    <%
        List<Menu> menuList = (List<Menu>) session.getAttribute("MenuList");
        if (menuList != null && !menuList.isEmpty()) {
            for (Menu menu : menuList) {
    %>
    <div class="card">
        <img src="<%= menu.getImagePath() %>" alt="<%= menu.getName() %>" class="restaurant-image">
        <div class="card-content">
            <h2 class="card-title"><%= menu.getName() %></h2>
            <p class="card-subtitle"><%= menu.getDescription() %></p>
            <p><strong>Price:</strong> ₹<%= menu.getPrice() %></p>
            <form action="CartServlet" method="post" class="cart-form">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="itemId" value="<%= menu.getMenuId() %>">
                <label for="quantity-<%= menu.getMenuId() %>">Qty:</label>
                <input type="number" id="quantity-<%= menu.getMenuId() %>" name="quantity" value="1" min="1" class="quantity-input">
                <button type="submit" class="view-menu-link">Add to Cart</button>
            </form>
        </div>
    </div>
    <% 
            }
        } else { 
    %>
    <div class="no-restaurants">
        <p>No menu items available for this restaurant.</p>
    </div>
    <% } %>
</div>
</body>
</html>
