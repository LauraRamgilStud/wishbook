package com.wishbook.repository;

import com.wishbook.user.User;
import com.wishbook.utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    @Value("${spring.datasource.url}")
    private String DB_URL;

    @Value("${spring.datasource.username}")
    private String UID;

    @Value("${spring.datasource.password}")
    private String PWD;

    public List<User> getAll(){
        List<User> userList = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection(DB_URL, UID, PWD);
            Statement statement = connection.createStatement();
            final String SQL_QUERY = "SELECT * FROM user";
            ResultSet resultSet = statement.executeQuery(SQL_QUERY);
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String fname = resultSet.getString(2);
                String lname = resultSet.getString(3);
                String email = resultSet.getString(4);
                String pw = resultSet.getString(5);
                User user = new User(id, fname, lname, email, pw);
                userList.add(user);
                System.out.println(user);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Could not query database");
            e.printStackTrace();
        }
        return userList;
    }

    public boolean checkUserExists(ArrayList<User> list, User user){
        for(User u : list){
            if(u.equals(user)){
                return true;
            }
        }
        return false;
    }
}











