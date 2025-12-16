package com.example.librarymanagement.service;

import com.example.librarymanagement.interfaces.SubscriptionServiceInterface;
import com.example.librarymanagement.model.Publication;
import com.example.librarymanagement.model.Subscription;
import com.example.librarymanagement.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService implements SubscriptionServiceInterface {

    private final SubscriptionRepository repository;

    public SubscriptionService(SubscriptionRepository repository) {
        this.repository = repository;
    }

    public List<Subscription> getALL() {
        return repository.findAll();
    }

    public Subscription save(Subscription subscription){
        Optional<Subscription> optional = repository.findById(subscription.getTitle());
        if (optional.isPresent()){
            System.out.println("Subscription already Exist");
            return null;
        }
        repository.save(subscription);
        return subscription;
    }

    public boolean hasNoChild(String name){
        Subscription subscription = repository.findById(name).orElse(null);
        return subscription.getAllotmentList().isEmpty();
    }

    public void delete(String title){
        repository.deleteById(title);
    }

    public void update(Subscription subscription){
        repository.save(subscription);
    }
}