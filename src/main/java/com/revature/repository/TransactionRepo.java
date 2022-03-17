package com.revature.repository;

import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.utility.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepo implements DAO<Transaction> {
    //create transaction record in DB
    @Override
    public void create(Transaction trans) {


        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "insert into transactions (amount, description, type, account_id) values (?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, trans.getTransAmount());
            stmt.setString(2, trans.getTransDescription());
            stmt.setString(3, trans.getTransType());
            stmt.setInt(4, trans.getAccount());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Your connection is bad and you should feel bad.");
        }

    }
    // retrieve transaction record using ID
    @Override
    public Transaction getById(int id) {
        Transaction trans = null;
        String sql = "select * from transactions where id = ?";



        try (Connection connection = ConnectionFactory.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()){
                trans = new Transaction(
                        resultSet.getInt("id"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("description"),
                        resultSet.getString("type"),
                        resultSet.getInt("account_id")
                );
            }
        } catch (SQLException e) {
            System.out.println("Your connection is bad and you should feel bad.");
        }

        return trans;
    }

    // retrieve transaction record using accountnum and transnumber
    public List<Transaction> getByAccountNum(int accnum){
        List<Transaction> transList = new ArrayList<>();
        Transaction trans =null;

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from transactions where account_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, accnum);

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                trans = new Transaction(
                        resultSet.getInt("id"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("description"),
                        resultSet.getString("type"),
                        resultSet.getInt("account_id")
                );
                transList.add(trans);
            }
        } catch (SQLException e) {
            System.out.println("Your connection is bad and you should feel bad.");
        }
        return transList;
    }
}
