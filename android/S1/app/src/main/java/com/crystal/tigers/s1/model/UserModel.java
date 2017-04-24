package com.crystal.tigers.s1.model;

/**
 * Created by vtruong8 on 09/03/2017.
 */
public class UserModel {
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    protected String userID;
    protected String userName;
    protected String email;
    protected String address;


}
