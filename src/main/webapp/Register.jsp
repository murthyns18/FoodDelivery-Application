<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Page</title>
    <link rel="stylesheet" href="register.css">
</head>
<body>
    <div class="container">
        <h1>Create Your Account</h1>
        <p class="signin-text">Already have an account? <a href="Login.jsp">Sign In</a></p>
        
        <form action="Register" method="post" class="form-container">
            <div class="form-group">
                <label for="username">Enter your name</label>
                <input type="text" id="username" name="username" required>
            </div>

            <div class="form-group">
                <label for="password">Enter your password</label>
                <input type="password" id="password" name="password" required>
            </div>

            <div class="form-group">
                <label for="confirmpassword">Confirm password</label>
                <input type="password" id="confirmpassword" name="confirmpassword" required>
            </div>

            <div class="form-group">
                <label for="email">Enter your email</label>
                <input type="email" id="email" name="email" required>
            </div>

            <div class="form-group">
                <label for="address">Enter your address</label>
                <textarea id="address" name="address" rows="3" required></textarea>
            </div>

            <button type="submit" class="register-btn">Register</button>
        </form>
    </div>
</body>
</html>
