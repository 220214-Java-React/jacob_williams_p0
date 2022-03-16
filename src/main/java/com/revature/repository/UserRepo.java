package com.revature.repository;

import com.revature.model.User;
import com.revature.utility.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepo implements DAO <User>{

    @Override
    public void create(User user) {


        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "insert into users(username, password) values (?, ?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Your connection is bad and you should feel bad.");
        }

    }

    @Override
    public User getById(int id) {
        User user = null;
        String sql = "select * from users where id = ?";


        try (Connection connection = ConnectionFactory.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(sql);

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

    @Override
    public void update(User user) {

    }

    @Override
    public void deleteById(int id) {


    }

    public User getByUserName (String username){
        User user = null;

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from users where username = ?";
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
