package com.example.appdevproject.Registration;

public class User {
    private Integer id; // this is the primary key
    private String userName;
    private String password;
    private String email;
    private String dob; //saved as a date in the db.

    public User(String userName, String email, String dob) {
        this.userName = userName;
        this.email = email;
        this.dob = dob;
    }

    //constructors
    public User(String userName, String password, String email, String dob) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.dob = dob;
    }

    public User(Integer id, String userName, String password, String email, String dob) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.dob = dob;
    }

    //be able to get the user's age , if they are loggin in on their bday show happy bday









    // GETTERS and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
