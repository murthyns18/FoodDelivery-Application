# FoodDelivery Application

**Project: Food Delivery Application**

**Technologies Used**

Front-End: Html, CSS, Javascript

Back-End: Java, JDBC, JSP, Servlets

Database: MySQ

**Project Overview** 

The Food Delivery application allows users to:

Visit the website, register, and log in.
Browse menus from various restaurants, search, filter, and add items to their cart.
Update the quantity of items in the cart and proceed to checkout.
Make a demo payment for the order. After payment, users can view order details and the delivery status.
Note: The payment page is only a demo and is not connected to a real payment gateway. Any credit card details will be accepted for demo orders.

**Software and Tools Required**

Java JDK 8 or higher 

Eclipse EE (Enterprise Edition)

Tomcat v8.0 or higher

MySQL Server

MySQL Workbench

Database Setup:

**Steps to run the project**

**Create the database**

Create the following tables

**User Table**

CREATE TABLE user (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    address VARCHAR(255)
);

**Restaurant Table**

CREATE TABLE restaurant (
    restaurantId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cuisineType VARCHAR(255),
    deliveryTime INT,
    address TEXT,
    ratings FLOAT,
    isActive BOOLEAN,
    imagePath VARCHAR(255)
);

**Menu Table**

CREATE TABLE menu (
    menuId INT AUTO_INCREMENT PRIMARY KEY,
    restaurantId INT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price INT,
    isAvailable BOOLEAN,
    imagePath VARCHAR(255),
    FOREIGN KEY (restaurantId) REFERENCES restaurant(restaurantId)
);

**Orders Table**

CREATE TABLE orders (
    ordersId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT,
    restaurantId INT,
    totalAmount DOUBLE,
    status VARCHAR(255),
    paymentMode VARCHAR(255),
    FOREIGN KEY (userId) REFERENCES user(userId),
    FOREIGN KEY (restaurantId) REFERENCES restaurant(restaurantId)
);

**OrderItems Table**

CREATE TABLE OrderItems (
    orderItemsId INT PRIMARY KEY AUTO_INCREMENT,
    orderId INT,
    menuId INT,
    quantity INT,
    itemTotal INT
);

**OrderHistory Table**

CREATE TABLE orderHistory (
    orderHistoryId INT AUTO_INCREMENT PRIMARY KEY,
    orderId INT,
    userId INT,
    totalAmount INT,
    status VARCHAR(255),
    FOREIGN KEY (orderId) REFERENCES orders(ordersId),
    FOREIGN KEY (userId) REFERENCES user(userId)
);

**Update the Connect Class**

    static String url = "jdbc:mysql://localhost:3306/db_name";
    static String un = "root";
    static String pwd = "ur_password";

    
**Running the Project on Tomcat**

**Configure Tomcat**

**If Tomcat is not set up in Eclipse**

Right-click on the project > Run As > Run On Server.
Manually define a new server and select Tomcat v8.0+.
Add the project and finish the setup.

**If Tomcat is already configured**

Right-click on the project > Run As > Run On Server.
Select Tomcat and finish.

**Check the Site**

Visit http://localhost:8080/FoodApplication/

If u face any problem like port is in already use then 

Change the Port
Open the Server tab > Double-click on Tomcat Server > Go to Ports.
Change the HTTP/1.1 port to 8083.
Save and start the server. Visit http://localhost:8080/FoodApplication/
