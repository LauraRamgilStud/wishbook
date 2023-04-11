package com.wishbook.user;

public class User {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String pw;

    //empty constructor, because ?
    public User(){}

    public User(String fname, String lname, String email, String pw){
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pw = pw;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
