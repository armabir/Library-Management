package com.example.librarymanagement.interfaces;

import com.example.librarymanagement.model.Subscription;

import java.util.List;

public interface SubscriptionServiceInterface {
    void toList();
    List<Subscription> getALL();
    Subscription save(Subscription subscription);
    Subscription getByTitle(String title);
    boolean exists(String title);
    void delete(String title);
    void update(Subscription subscription);
}
