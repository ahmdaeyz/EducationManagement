package com.Management;

public class User {
    private String usrName;
    private String fullName;
    private String email;
    private String password;
    public User(String usrName,String fullName,String email,String password){
        this.usrName=usrName;
        this.fullName=fullName;
        this.email=email;
        this.password=password;
    }
    public User(){}
    public String getUsrName() {
        return usrName;
    }
    public String getFullName() {
        return fullName;
    }
    public String getPassword() {
        return password;
    }
}
