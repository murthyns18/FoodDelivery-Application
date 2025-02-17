package com.ns.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ns.food.connector.Connect;
import com.ns.food.dao.MenuDAO;
import com.ns.food.model.Menu;

public class MenuDAOImpl implements MenuDAO 
{

    static final String INSERT = "INSERT INTO menu(restaurantId, name, description, price, isAvailable, imagePath) VALUES (?, ?, ?, ?, ?, ?)";
    static final String FETCH_ALL = "SELECT * FROM menu";
    static final String FETCH_BY_ID = "SELECT * FROM menu WHERE menuId = ?";
    static final String UPDATE = "UPDATE menu SET price = ? WHERE menuId = ?";
    static final String DELETE = "DELETE FROM menu WHERE menuId = ?";
    static final String FETCH_BY_RESTAURANT = "SELECT * FROM menu WHERE restaurantId = ?";

    private Connection con;

    public MenuDAOImpl()
    {
        try 
        {
            con = Connect.connect();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    public int insert(Menu menu) 
    {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT)) 
        {
            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getName());
            pstmt.setString(3, menu.getDescription());
            pstmt.setInt(4, menu.getPrice());
            pstmt.setBoolean(5, menu.isAvailable());
            pstmt.setString(6, menu.getImagePath());

            return pstmt.executeUpdate();
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Menu> fetchAll() 
    {
        ArrayList<Menu> menuList = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet resultSet = stmt.executeQuery(FETCH_ALL)) {
            while (resultSet.next()) {
                menuList.add(new Menu(
                        resultSet.getInt("menuId"),
                        resultSet.getInt("restaurantId"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("price"),
                        resultSet.getBoolean("isAvailable"),
                        resultSet.getString("imagePath")
                ));
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public Menu fetchOne(int menuId) 
    {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_ID))
        {
            pstmt.setInt(1, menuId);
            try (ResultSet resultSet = pstmt.executeQuery())
            {
                if (resultSet.next())
                {
                    return new Menu(
                            resultSet.getInt("menuId"),
                            resultSet.getInt("restaurantId"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getInt("price"),
                            resultSet.getBoolean("isAvailable"),
                            resultSet.getString("imagePath")
                    );
                }
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(int menuId, int price) 
    {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE)) 
        {
            pstmt.setInt(1, price);
            pstmt.setInt(2, menuId);
            return pstmt.executeUpdate();
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int menuId) 
    {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE))
        {
            pstmt.setInt(1, menuId);
            return pstmt.executeUpdate();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Menu> fetchByRestaurantId(int restaurantId) 
    {
        List<Menu> menuList = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_RESTAURANT)) 
        {
            pstmt.setInt(1, restaurantId);
            try (ResultSet resultSet = pstmt.executeQuery()) 
            {
                while (resultSet.next())
                {
                    menuList.add(new Menu(
                            resultSet.getInt("menuId"),
                            resultSet.getInt("restaurantId"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getInt("price"),
                            resultSet.getBoolean("isAvailable"),
                            resultSet.getString("imagePath")
                    ));
                }
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public Menu getMenu(int itemId)
    {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_ID)) 
        {
            pstmt.setInt(1, itemId);
            try (ResultSet resultSet = pstmt.executeQuery()) 
            {
                if (resultSet.next())
                {
                    return new Menu(
                            resultSet.getInt("menuId"),
                            resultSet.getInt("restaurantId"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getInt("price"),
                            resultSet.getBoolean("isAvailable"),
                            resultSet.getString("imagePath")
                    );
                }
            }
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
