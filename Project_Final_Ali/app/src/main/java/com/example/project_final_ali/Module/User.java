package com.example.project_final_ali.Module;

public class User {

    public String fullname ,email , type ,token;
//    public int id;



    public User() {
    }
//
    public User(String email, String name, String type) {
        this.email = email;
        this.fullname = name;
        this.type = type;
    }

    public User(String fullname, String email) {
        this.fullname = fullname;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}





