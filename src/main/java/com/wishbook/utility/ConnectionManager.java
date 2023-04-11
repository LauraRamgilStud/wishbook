package com.wishbook.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Database connection singleton
public class ConnectionManager {
    private static Connection connection = null;

    //Get a connection to the database
    public static Connection getConnection(String db_url, String uid, String pwd) {
        // Check if there is a connection
        if(connection == null){
            //if not - initialize the connection
            try{
                connection = DriverManager.getConnection(db_url, uid, pwd);
            }catch (SQLException e){
                System.out.println("Could not connect to DB");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
