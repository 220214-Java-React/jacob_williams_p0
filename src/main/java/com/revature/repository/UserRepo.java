package com.revature.repository;

import com.revature.model.User;
import com.revature.utility.ConnectionFactory;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo implements DAO <User>{

    //create user record in DB
    @Override
    public void create(User user) {


        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "insert into user(username, password, firstname, lastname) values (?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getUserFirstName());
            stmt.setString(4, user.getUserLastName());

            stmt.executeUpdate();
            System.out.println("User creation complete!");
        } catch (SQLException e) {
            System.out.println("Your connection is bad and you should feel bad.");
        }

    }
    // retrieve user record using ID
    @Override
    public User getById(int id) {
        User user = null;
        String sql = "select * from user where id = ?";



        try (Connection connection = ConnectionFactory.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()){
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname")
                );
            }
        } catch (SQLException e) {
            System.out.println("Your connection is bad and you should feel bad.");
        }

        return user;
    }

    // retrieve user record using userName
    public User getByUserName (String username){
        User user = null;

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from user where username = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, username);

            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()){
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname")
                );
            }
        } catch (SQLException e) {
            System.out.println("Your connection is bad and you should feel bad.");
        }
        return user;
    }
}
