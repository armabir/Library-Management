package com.example.librarymanagement.service;

import com.example.librarymanagement.interfaces.UserServiceInterface;
import com.example.librarymanagement.model.Publication;
import com.example.librarymanagement.model.User;
import com.example.librarymanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    public final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getALL(){
        return repository.findAll();
    }

    public User saveUser(User user){
        Optional<User> optional = repository.findById(user.getName());
        if (optional.isPresent()){
            System.out.println("User already Exist");
            return null;
        }
        repository.save(user);
        return user;
    }

    public User exists(String email, String pass){
        return repository.findById(email).orElse(null);
    }

    public User getByEmail(String email){
        return repository.findById(email).orElse(null);
    }

    public void delete(String email){
        repository.deleteById(email);
    }

    public void update(User user){
        repository.save(user);
    }
}
