package com.wishbook.repository;

import com.wishbook.models.WishList;
import com.wishbook.utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishListRepository {

    @Value("${spring.datasource.url}")
    private String DB_URL;

    @Value("${spring.datasource.username}")
    private String UID;

    @Value("${spring.datasource.password}")
    private String PWD;

    public List<WishList> getWishListsByUserID(int userID){
        List<WishList> list = new ArrayList<>();
        try{
            final String QUERY = "SELECT * FROM Wishlist WHERE user_id = ?";
            Connection connection = ConnectionManager.getConnection(DB_URL, UID, PWD);
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String listName = resultSet.getString(3);
                String occasion = resultSet.getString(4);
                Blob coverPic = resultSet.getBlob(5);
                WishList wishList = new WishList(userID, listName, occasion, coverPic);
                wishList.setId(id);
                list.add(wishList);
            }

        }catch (SQLException e){
            System.out.println("Could not find wishlists");
            e.printStackTrace();
        }
        return list;
    }

    public void addWishList(WishList wishList){
        try{
            Connection connection = ConnectionManager.getConnection(DB_URL, UID, PWD);
            final String CREATE_QUERY = "INSERT INTO Wishlist(user_id, list_name, occasion, cover_pic) " +
                                        "VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);

            preparedStatement.setInt(1, wishList.getUser_id());
            preparedStatement.setString(2, wishList.getList_name());
            preparedStatement.setString(3, wishList.getOccasion());
            preparedStatement.setBlob(4, wishList.getCover_pic());

            //Execute statement
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println("Could not create wishlist");
            e.printStackTrace();
        }
    }

    public void updateWishList(WishList wishList){
        // SQL QUERY
        final String UPDATE_QUERY = "UPDATE Wishlist " +
                                    "SET list_name = ?, occasion = ?, cover_pic = ?" +
                                    "WHERE id = ?";

        try{
            Connection connection = ConnectionManager.getConnection(DB_URL, UID, PWD);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            //Set parameters
            int id = wishList.getId();
            String list_name = wishList.getList_name();
            String occasion = wishList.getOccasion();
            Blob cover_pic = wishList.getCover_pic();
            preparedStatement.setString(1, list_name);
            preparedStatement.setString(2, occasion);
            preparedStatement.setBlob(3, cover_pic);
            preparedStatement.setInt(4, id);

            //execute statement
            preparedStatement.executeUpdate();


        }catch (SQLException e){
            System.out.println("Could not update wishlist");
            e.printStackTrace();
        }
    }

    public void deleteWishListByID(int id){
        //SQL QUERY
        final String DELETE_QUERY = "DELETE FROM wishlist WHERE id = ?";

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
