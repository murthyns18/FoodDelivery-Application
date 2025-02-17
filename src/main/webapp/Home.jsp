<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.ns.food.model.Restaurant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Home Page</title>
    <style>
        .navbar {
            background-color: #ffffff;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            padding: 1rem 2rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            z-index: 1000;
        }

        .logo {
            display: flex;
            align-items: center;
        }

        .site-title {
            font-size: 1.5rem;
            font-weight: bold;
            color: #1a1a1a;
        }

        .nav-links {
            display: flex;
            gap: 2rem;
            align-items: center;
        }

        .nav-link {
            text-decoration: none;
            color: #4a5568;
            display: flex;
            align-items: center;
            gap: 0.5rem;
            font-weight: 500;
            transition: color 0.2s ease;
        }

        .nav-link:hover {
            color: #2d3748;
        }

        .cart-count {
            background-color: #ef4444;
            color: white;
            padding: 0.125rem 0.375rem;
            border-radius: 9999px;
            font-size: 0.75rem;
        }

        .auth-buttons {
            display: flex;
            gap: 1rem;
            align-items: center;
        }

        .btn {
            padding: 0.5rem 1rem;
            border-radius: 0.375rem;
            font-weight: 500;
            text-decoration: none;
            transition: all 0.2s ease;
        }

        .btn-primary {
        background: linear-gradient(120deg, #4a90e2, #67b26f);
            color: white;
            border: none;
        }

        .btn-primary:hover {
            background-color: #4338ca;
        }

        .btn-secondary {
            background: linear-gradient(120deg, #4a90e2, #67b26f);
            color: #4f46e5;
            border: 1px solid #4f46e5;
        }

        .btn-secondary:hover {
            background-color: #f3f4f6;
        }

        /* Existing styles */
        .search-container {
            margin-top: 80px;
            padding: 1rem;
        }

        .search-filters {
            display: flex;
            gap: 1rem;
            align-items: center;
            flex-wrap: wrap;
        }

        .search-group {
            position: relative;
            flex: 1;
        }

        .search-input {
            width: 100%;
            padding: 0.5rem 1rem 0.5rem 2.5rem;
            border: 1px solid #e2e8f0;
            border-radius: 0.375rem;
        }

        .search-icon {
            position: absolute;
            left: 0.75rem;
            top: 50%;
            transform: translateY(-50%);
        }

        .filter-group {
            position: relative;
        }

        .filter-select {
            padding: 0.5rem;
            border: 1px solid #e2e8f0;
            border-radius: 0.375rem;
            min-width: 120px;
        }

        .card-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 2rem;
            padding: 2rem;
        }

        .card {
            border: 1px solid #e2e8f0;
            border-radius: 0.5rem;
            overflow: hidden;
            transition: transform 0.2s;
        }

        .card:hover {
            transform: translateY(-4px);
        }

        .restaurant-image {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }

        .card-content {
            padding: 1rem;
        }

        .card-title {
            font-size: 1.25rem;
            font-weight: 600;
            margin-bottom: 0.5rem;
        }

        .card-subtitle {
            color: #4a5568;
            margin-bottom: 1rem;
        }

        .restaurant-info {
            display: flex;
            justify-content: space-between;
            margin-bottom: 1rem;
        }

        .delivery-time, .ratings {
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .address {
            color: #4a5568;
            margin-bottom: 1rem;
        }

        .view-menu-link {
            display: inline-block;
            padding: 0.5rem 1rem;
            background: linear-gradient(120deg, #4a90e2, #67b26f);
            color: white;
            text-decoration: none;
            border-radius: 0.375rem;
            transition: background-color 0.2s;
        }

        .view-menu-link:hover {
            background-color: #4338ca;
        }

        .no-restaurants {
            text-align: center;
            padding: 2rem;
            color: #4a5568;
        }

        @media (max-width: 768px) {
            .navbar {
                padding: 1rem;
            }
            
            .nav-links {
                display: none;
            }
            
            .search-filters {
                flex-direction: column;
            }
            
            .search-group {
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <nav class="navbar">
        <div class="logo">
            <span class="site-title">FoodieHub</span>
        </div>
        
        <div class="nav-links">
            <a href="#" class="nav-link">
                <span>🎁</span>
                Offers
            </a>
            <a href="#" class="nav-link">
                <span>❓</span>
                Help
            </a>
            <a href="Cart.jsp" class="nav-link">
                <span>🛒</span>
                Cart
            </a>
        </div>

        <div class="auth-buttons">
            <% if(session.getAttribute("username") == null) { %>
                <a href="Login.jsp" class="btn btn-secondary">Sign In</a>
                <a href="Register.jsp" class="btn btn-primary">Sign Up</a>
            <% } else { %>
                <span>Welcome, <%= session.getAttribute("username") %></span>
            <% } %>
        </div>
    </nav>

    <div class="search-container">
        <div class="search-filters">
            <div class="search-group">
                <i class="search-icon">🔍</i>
                <input 
                    type="text" 
                    id="searchInput"
                    placeholder="Search restaurants or cuisine..."
                    class="search-input"
                >
            </div>

            <div class="filter-group">
                <select id="cuisineFilter" class="filter-select">
                    <option value="">All Cuisines</option>
                    <option value="Italian">Italian</option>
                    <option value="Chinese">Chinese</option>
                    <option value="Indian">Indian</option>
                    <option value="Japanese">Japanese</option>
                </select>
            </div>

            <div class="filter-group">
                <i class="rating-icon">⭐</i>
                <select id="ratingFilter" class="filter-select">
                    <option value="">Any Rating</option>
                    <option value="4.5">4.5+</option>
                    <option value="4.0">4.0+</option>
                    <option value="3.5">3.5+</option>
                </select>
            </div>

            <div class="filter-group">
                <i class="time-icon">⏰</i>
                <select id="timeFilter" class="filter-select">
                    <option value="">Any Time</option>
                    <option value="30">Under 30 mins</option>
                    <option value="45">Under 45 mins</option>
                    <option value="60">Under 60 mins</option>
                </select>
            </div>
        </div>
    </div>

    <div id="restaurantContainer" class="card-container">
        <% 
        List<Restaurant> restaurantList = (List<Restaurant>) session.getAttribute("RestaurantList");
        if (restaurantList != null && !restaurantList.isEmpty()) {
            for (Restaurant restaurant : restaurantList) { 
        %>
        <div class="card" 
             data-name="<%= restaurant.getName().toLowerCase() %>"
             data-cuisine="<%= restaurant.getCuisineType() %>"
             data-rating="<%= restaurant.getRatings() %>"
             data-time="<%= restaurant.getDeliveryTime() %>">
            <img 
                src="<%= restaurant.getImagePath() %>" 
                alt="<%= restaurant.getName() %>" 
                class="restaurant-image" 
                onerror="this.onerror=null; this.src='images/default.jpg';"
            >
            <div class="card-content">
                <h2 class="card-title"><%= restaurant.getName() %></h2>
                <p class="card-subtitle"><%= restaurant.getCuisineType() %> Cuisine</p>
                <div class="restaurant-info">
                    <p class="delivery-time">
                        <i class="time-icon">⏰</i>
                        <%= restaurant.getDeliveryTime() %> mins
                    </p>
                    <p class="ratings">
                        <i class="star-icon">⭐</i>
                        <span><%= restaurant.getRatings() %> / 5</span>
                    </p>
                </div>
                <p class="address"><%= restaurant.getAddress() %></p>
                <a href="MenuServlet?restaurantId=<%= restaurant.getRestaurantId() %>" class="view-menu-link">
                    View Menu
                </a>
            </div>
        </div>
        <% } %>
    </div>
    <% } else { %>
        <p class="no-restaurants">No restaurants available at the moment.</p>
    <% } %>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const searchInput = document.getElementById('searchInput');
            const cuisineFilter = document.getElementById('cuisineFilter');
            const ratingFilter = document.getElementById('ratingFilter');
            const timeFilter = document.getElementById('timeFilter');
            const cards = document.querySelectorAll('.card');

            function filterRestaurants() {
                const searchTerm = searchInput.value.toLowerCase();
                const selectedCuisine = cuisineFilter.value;
                const selectedRating = parseFloat(ratingFilter.value) || 0;
                const selectedTime = parseInt(timeFilter.value) || Number.MAX_VALUE;

                cards.forEach(card => {
                    const name = card.dataset.name;
                    const cuisine = card.dataset.cuisine;
                    const rating = parseFloat(card.dataset.rating);
                    const time = parseInt(card.dataset.time);

                    const matchesSearch = name.includes(searchTerm) || 
                                       cuisine.toLowerCase().includes(searchTerm);
                    const matchesCuisine = !selectedCuisine || cuisine === selectedCuisine;
                    const matchesRating = rating >= selectedRating;
                    const matchesTime = time <= selectedTime;

                    if (matchesSearch && matchesCuisine && matchesRating && matchesTime) {
                        card.style.display = '';
                    } else {
                        card.style.display = 'none';
                    }
                });

                const visibleCards = document.querySelectorAll('.card[style=""]').length;
                let noResults = document.querySelector('.no-results');
                
                if (visibleCards === 0) {
                    if (!noResults) {
                        noResults = document.createElement('p');
                        noResults.className = 'no-restaurants no-results';
                        noResults.textContent = 'No restaurants match your search criteria.';
                        document.getElementById('restaurantContainer').appendChild(noResults);
                    }
                    noResults.style.display = '';
                } else if (noResults) {
                    noResults.style.display = 'none';
                }
            }

            searchInput.addEventListener('input', filterRestaurants);
            cuisineFilter.addEventListener('change', filterRestaurants);
            ratingFilter.addEventListener('change', filterRestaurants);
            timeFilter.addEventListener('change', filterRestaurants);
        });
    </script>
</body>
</html>
