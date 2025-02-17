package com.ns.food.model;

public class User 
{
    private int userId;
    private String userName;
    private String password;
    private String email;
    private String address;

    public User() 
    {
        super();
    }

    public User(String userName, String password, String email, String address) 
    {
        super();
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public String getUserName() 
    {
        return userName;
    }

    public int getUserId() 
    {
        return userId;
    }

    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Override
    public String toString() 
    {
        return userId + "  " + userName + "  " + email + "  " + address;
    }
}
