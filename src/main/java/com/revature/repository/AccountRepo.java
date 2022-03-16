package com.revature.repository;

import com.revature.model.Account;
import com.revature.utility.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepo implements DAO<Account>{
    //create account record in DB
    @Override
    public void create(Account account) {


        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "insert into account(name, type, balance, main_user, joint_user) values (?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, account.getAcctName());
            stmt.setString(2, account.getAcctType());
            stmt.setDouble(3, account.getAcctBalance());
            stmt.setInt(4, account.getMainUser());
            stmt.setInt(5, account.getJointUser());

            stmt.executeUpdate();
            System.out.println("User creation complete!");
        } catch (SQLException e) {
            System.out.println("Your connection is bad and you should feel bad.");
        }

    }
    // retrieve account record using ID
    @Override
    public Account getById(int id) {
        Account account = null;
        String sql = "select * from account where id = ?";



        try (Connection connection = ConnectionFactory.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()){
                account = new Account(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getDouble("balance"),
                        resultSet.getInt("main_user"),
                        resultSet.getInt("joint_user")
                );
            }
        } catch (SQLException e) {
            System.out.println("Your connection is bad and you should feel bad.");
        }

        return account;
    }

    // retrieve user record using accountName
    public Account getByAccountName (String username){
        Account account = null;

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from users where username = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, username);

            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()){
                account = new Account(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getDouble("balance"),
                        resultSet.getInt("main_user"),
                        resultSet.getInt("joint_user")
                );
            }
        } catch (SQLException e) {
            System.out.println("Your connection is bad and you should feel bad.");
        }
        return account;
    }
}
