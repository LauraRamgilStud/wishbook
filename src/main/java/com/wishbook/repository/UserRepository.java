package com.wishbook.repository;

import com.wishbook.models.User;
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
                User user = new User(fname, lname, email, pw);
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

    //
    public boolean checkIfUserExists(String eMail){
        final String FIND_QUERY = "SELECT * FROM user WHERE email = ?";
        try {
            //db connection
            Connection connection = ConnectionManager.getConnection(DB_URL, UID, PWD);

            //prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);

            //execute statement
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            String email = resultSet.getString(4);

            if(email != null){
                return true;
            }

        } catch (SQLException e){
            System.out.println("Could not find user");
            e.printStackTrace();
        }

        return false;
    }



    public void addUser(User user){
        try{
            Connection connection = ConnectionManager.getConnection(DB_URL, UID, PWD);
            final String CREATE_QUERY = "INSERT INTO user(fname, lname, email, password) VALUES  (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);

            //set attributes i prepared statement
            preparedStatement.setString(1, user.getFname());
            preparedStatement.setString(2, user.getLname());
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());

            //execute statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not create user");
            e.printStackTrace();
        }
    }


}