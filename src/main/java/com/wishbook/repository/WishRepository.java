package com.wishbook.repository;

import com.wishbook.models.Wish;
import com.wishbook.models.WishList;
import com.wishbook.utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class WishRepository {

    @Value("${spring.datasource.url}")
    private String DB_URL;

    @Value("${spring.datasource.username}")
    private String UID;

    @Value("${spring.datasource.password}")
    private String PWD;

    public List<Wish> getWishesByWishlistID(int wishListID){
        List<Wish> list = new ArrayList<>();
        try{
            final String QUERY = "SELECT * FROM wishbook.wish WHERE wishlist_id = ?";
            Connection connection = ConnectionManager.getConnection(DB_URL, UID, PWD);
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setInt(1, wishListID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String wishName = resultSet.getString(3);
                String description = resultSet.getString(4);
                String price = resultSet.getString(5);
                String quantity = resultSet.getString(6);
                byte[] wishPic = resultSet.getBytes(7);
                String base64Pic = Base64.getEncoder().encodeToString(wishPic);
                String url = resultSet.getString(8);
                Wish wish = new Wish(wishListID, wishName, description, price, quantity, base64Pic, url);
                wish.setWish_pic(wishPic);
                wish.setId(id);
                list.add(wish);
            }

        }catch (SQLException e){
            System.out.println("Could not find wishlists");
            e.printStackTrace();
        }
        return list;
    }

    public void addWish(Wish wish){
        try{
            Connection connection = ConnectionManager.getConnection(DB_URL, UID, PWD);
            final String CREATE_QUERY = "INSERT INTO wishbook.Wish(wishlist_id, wish_name, description, price, quantity, wish_pic, url) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);

            preparedStatement.setInt(1, wish.getWishlist_id());
            preparedStatement.setString(2, wish.getWish_name());
            preparedStatement.setString(3, wish.getDescription());
            preparedStatement.setString(4, wish.getPrice());
            preparedStatement.setString(5, wish.getQuantity());
            preparedStatement.setBytes(6, wish.getWish_pic());
            preparedStatement.setString(7, wish.getUrl());

            //Execute statement
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println("Could not create wishlist");
            e.printStackTrace();
        }
    }

    public void updateWish(Wish wish){
        // SQL QUERY
        final String UPDATE_QUERY = "UPDATE wishbook.wish " +
                "SET wish_name = ?, description = ?, price = ?, quantity = ?, wish_pic = ?, url = ?" +
                "WHERE id = ?";

        try{
            Connection connection = ConnectionManager.getConnection(DB_URL, UID, PWD);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            //Set parameters
            preparedStatement.setString(2, wish.getWish_name());
            preparedStatement.setString(3, wish.getDescription());
            preparedStatement.setString(4, wish.getPrice());
            preparedStatement.setString(5, wish.getQuantity());
            preparedStatement.setBytes(6, wish.getWish_pic());
            preparedStatement.setString(7, wish.getUrl());

            //execute statement
            preparedStatement.executeUpdate();


        }catch (SQLException e){
            System.out.println("Could not update wishlist");
            e.printStackTrace();
        }
    }

    public void deleteWishByID(int id){
        //SQL QUERY
        final String DELETE_QUERY = "DELETE FROM wishbook.wish WHERE id = ?";

        try{
            Connection connection = ConnectionManager.getConnection(DB_URL, UID, PWD);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);

            //set parameter
            preparedStatement.setInt(1, id);

            //execute statement
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Could not delete product");
            e.printStackTrace();
        }
    }

}
