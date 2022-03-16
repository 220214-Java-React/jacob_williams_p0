package com.revature.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Connection conn;

    private static final String url = "jdbc:postgresql://projectzerodatabase.c3oa3kme4wem.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=PJZero";
    private static final String username = "postgres";
    private static final String password = "MooDB272";

    // this method makes a connection instance, if one is needed due to closure or one not yet created
    public static Connection getConnection() throws SQLException {

        if(conn == null || conn.isClosed()){
            conn = DriverManager.getConnection(url, username, password);
        }

        return conn;
    }

    private ConnectionFactory(){}

}
