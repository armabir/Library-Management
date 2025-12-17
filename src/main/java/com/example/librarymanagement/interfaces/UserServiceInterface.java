package com.example.librarymanagement.interfaces;

import com.example.librarymanagement.model.User;

import java.util.List;

public interface UserServiceInterface {
    User saveUser(User user);
    List<User> getALL();
    User exists(String email, String pass);
    public User getByEmail(String email);
    void delete(String email);
    void update(User user);
}
