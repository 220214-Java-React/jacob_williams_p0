package com.revature.repository;

import com.revature.model.Account;
import com.revature.utility.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepo implements DAO<Account>{
    //create account record in DB
    @Override
    public void create(Account account) {


        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "insert into account (account_name, type, balance, user_id) values (?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, account.getAcctName());
            stmt.setString(2, account.getAcctType());
            stmt.setDouble(3, account.getAcctBalance());
            stmt.setInt(4, account.getUser());


            stmt.executeUpdate();
            System.out.println("Account creation complete!");
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
                        resultSet.getString("account_name"),
                        resultSet.getString("type"),
                        resultSet.getDouble("balance"),
                        resultSet.getInt("user")
                );
            }
        } catch (SQLException e) {
            System.out.println("Your connection is bad and you should feel bad.");
        }

        return account;
    }

    // retrieve user record using accountName
    public Account getByAccountNameAndUserid (String acctname, int userid){
        Account account = null;

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from account where account_name = ? and user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, acctname);
            stmt.setInt(2, userid);

            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()){
                account = new Account(
                        resultSet.getInt("id"),
                        resultSet.getString("account_name"),
                        resultSet.getString("type"),
                        resultSet.getDouble("balance"),
                        resultSet.getInt("user_id")
                );
            }
        } catch (SQLException e) {
            System.out.println("Your connection is bad and you should feel bad.");
        }
        return account;
    }
    //find and return all accounts attached to a single user
    public List<Account> getByUserid (int userid){
        Account account = null;
        List<Account> foundAccounts = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from account where user_id = ?;";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, userid);

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                account = new Account(
                        resultSet.getInt("id"),
                        resultSet.getString("account_name"),
                        resultSet.getString("type"),
                        resultSet.getDouble("balance"),
                        resultSet.getInt("user_id")
                );
                foundAccounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Your connection is bad and you should feel bad.");
        }
        return foundAccounts;
    }
}
