package com.app.RESTdemo.student;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String Message){
        super(Message);
    }
}
