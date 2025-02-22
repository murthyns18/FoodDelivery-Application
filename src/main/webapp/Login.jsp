<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="login.css">
</head>
<body>
    
    	<div class="container">
        <h1>Sign In To Your Account</h1>
        <p class="signup-text">Don't have an account? <a href="Register.jsp">Sign Up</a></p>
        
        <form action="Login" method="post" class="form-container">
            <div class="form-group">
                <label for="email">Enter your email</label>
                <input type="email" id="email" name="email" required>
            </div>
            
            <div class="form-group">
                <label for="password">Enter your password</label>
                <input type="password" id="password" name="password" required>
            </div>
            
            <button type="submit" class="login-btn">Login</button>
        </form>
    </div>

</body>
</html>
