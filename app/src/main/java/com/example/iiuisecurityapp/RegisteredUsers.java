package com.example.iiuisecurityapp;

public class RegisteredUsers {
    String name;
    String email00;
    String password;
    public RegisteredUsers(){

    }
    public  RegisteredUsers(String name,String email0,String password){
        this.name=name;
        this.email00=email0;
        this.password=password;
    }


    public RegisteredUsers(String enailo, String passwardo) {
        this.email00=enailo;
        this.password=passwardo;
    }
}
