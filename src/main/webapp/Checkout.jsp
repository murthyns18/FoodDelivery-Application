<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout</title>
    <link rel="stylesheet" href="checkout.css">
</head>
<body>

    <div class="checkout-container">
        <h2>Checkout</h2>

        <form action="CheckoutServlet" method="post">
            <label for="address">Delivery Address:</label>
            <textarea id="address" name="address" required></textarea>

            <label for="payment">Payment Method:</label>
            <select id="payment" name="payment" required>
                <option value="Credit Card">Credit Card</option>
                <option value="Debit Card">Debit Card</option>
                <option value="Cash">Cash on Delivery</option>
            </select>

            <input type="submit" value="Place Order">
        </form>
    </div>

</body>
</html>
