package com.ns.food.model;

public class CartItem 
{
    private int itemId;
    private int restaurantId;
    private String name;
    private int price;
    private int quantity;

    public CartItem() 
    {
        super();
    }

    public CartItem(int itemId, int restaurantId, String name, int price, int quantity) 
    {
        this.itemId = itemId;
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getItemId()
    {
        return itemId;
    }

    public void setItemId(int itemId) 
    {
        this.itemId = itemId;
    }

    public int getRestaurantId()
    {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId)
    {
        this.restaurantId = restaurantId;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public int getPrice() 
    {
        return price;
    }

    public void setPrice(int price) 
    {
        this.price = price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
    }
}
