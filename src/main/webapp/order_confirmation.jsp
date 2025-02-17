<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ns.food.model.Orders" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="orderConfirmation.css">
</head>
<body>
    <div class="bike-container"></div>

    <div class="confirmation-container">
        <h1>Your order has been placed successfully</h1>
        <img src="right.png" alt="Success" style="width: 100px; margin-bottom: 15px;" />
        <%
            Orders order = (Orders) session.getAttribute("order");

            if (order != null) 
            {
        %>
        <div class="order-details">
            <p>Order ID: <strong><%= order.getOrdersId() %></strong></p>
            <p>Total Amount Paid: <strong>₹<%= order.getTotalAmount() %></strong></p>
        </div>
        <div class="next-steps">
            <a href="Home.jsp" class="btn">Return to Home</a>
        </div>
        <% } else { %>
        <div class="error-message">
            <h2>Something went wrong!</h2>
            <p>We couldn't retrieve your order details. Please contact support.</p>
        </div>
        <% } %>
    </div>

    <script>
        window.addEventListener('load', () => {
            const bike = document.querySelector('.bike-container');
            bike.style.display = 'block'; 
        });
    </script>
</body>
</html>
