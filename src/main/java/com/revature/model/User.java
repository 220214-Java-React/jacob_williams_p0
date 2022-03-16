package com.revature.model;

import java.util.List;

public class User {
    private int userID; //unique user ID, will be primary key in USER table, and foreign key in other tables
    private String userName; //unique username, will be used for logging in
    private String password;//password for login. TODO create rules for secure password.
    private String userFirstName;// user's first name
    private String userLastName;// user's last name

    public User(int id, String userName, String password, String fName, String lName){}



    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }
}
