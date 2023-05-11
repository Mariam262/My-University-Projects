package com.example.project;

public class RegisterData
{
    int id;
    String username, email ,pass;

    public RegisterData(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RegisterData(String em, String pass) {
    }

    public RegisterData() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPass() {
        return pass;
    }


    public void setPass(String pass) {
        this.pass = pass;
    }

}
