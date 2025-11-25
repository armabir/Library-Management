package com.example.librarymanagement.interfaces;

import com.example.librarymanagement.model.User;

import java.util.List;

public interface UserServiceInterface {
    User saveUser(User user);
    User getByEmail(String email);
    boolean exists(String email, String password);
    List<User> getALL();
    void delete(String email);
    void update(User user);
}
