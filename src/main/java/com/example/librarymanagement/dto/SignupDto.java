package com.example.librarymanagement.dto;

import com.example.librarymanagement.model.User;

public record SignupDto(String name, String email, String pass) {
    public User toUser(){
        return new User(name, email, pass);
    }
}
