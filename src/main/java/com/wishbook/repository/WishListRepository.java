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
                byte[] coverPic = resultSet.getBytes(5);
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

}
