package com.example.librarymanagement.interfaces;

import com.example.librarymanagement.model.Subscription;

import java.util.List;

public interface SubscriptionServiceInterface {
    List<Subscription> getALL();
    Subscription save(Subscription subscription);
    void delete(String title);
    void update(Subscription subscription);
}
