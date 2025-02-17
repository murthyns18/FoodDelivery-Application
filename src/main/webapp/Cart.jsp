<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ns.food.model.Cart" %>
<%@ page import="com.ns.food.model.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart</title>
    <link rel="stylesheet" href="cart.css">
</head>
<body>
    <div class="cart-container">
        <h1>Your Cart</h1>
        <%
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart == null || cart.getItems().isEmpty()) 
            {
        %>
            <div class="no-menu">
                <p>Your cart is empty.</p>
                <a href="Menu.jsp" class="browse-menu">Browse Menu</a>
            </div>
        <%
            } 
            else 
            {
                int totalAmount = cart.getTotalAmount();
        %>
        <div class="cart-items">
            <%
                for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) 
                {
                    CartItem item = entry.getValue();
            %>
            <div class="cart-item">
                <h3><%= item.getName() %></h3>
                <p>Price: ₹<%= item.getPrice() %></p>
                
                <div class="cart-item-actions">
                    <form action="CartServlet" method="post" class="action-form">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                        <label for="quantity-<%= item.getItemId() %>">Quantity:</label>
                        <input type="number" id="quantity-<%= item.getItemId() %>" name="quantity" 
                               value="<%= item.getQuantity() %>" min="1" class="quantity-input">
                        <button type="submit" class="update-button">Update</button>
                    </form>

                    <form action="CartServlet" method="post" class="action-form">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                        <button type="submit" class="remove-button">Remove</button>
                    </form>
                </div>

                <p>Total for this item: ₹<%= item.getPrice() * item.getQuantity() %></p>
            </div>
            <%
                }
            %>
        </div>
        <div class="cart-summary">
            <h3>Total Amount: ₹<%= totalAmount %></h3>
            <div class="cart-summary-buttons">
                <a href="Menu.jsp" class="add-to-cart-btn">Add More Items</a>
                <a href="Checkout.jsp" class="add-to-cart-btn">Proceed To Checkout</a>
            </div>
        </div>
        <%
            }
        %>
    </div>
</body>
</html>
