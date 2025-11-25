package com.example.librarymanagement.service;

import com.example.librarymanagement.interfaces.UserServiceInterface;
import com.example.librarymanagement.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceInterface {
    public final List<User> userList = new ArrayList<>();

    public User saveUser(User user){
        if(getByEmail(user.getEmail()) != null){
            System.out.println("User already Exist");
            return null;
        }
        userList.add(user);
        return user;
    }

    public User getByEmail(String email){
        return userList.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst().orElse(null);
    }

    public boolean exists(String email, String password){
        return userList.stream()
                .anyMatch(user -> user.getEmail().equals(email) && user.getPass().equals(password));
    }

    public List<User> getALL(){
        return userList;
    }

    public void delete(String email){
        userList.removeIf(user -> user.getEmail().equals(email));
    }

    public void update(User user){
        delete(user.getEmail());
        saveUser(user);
    }
}
