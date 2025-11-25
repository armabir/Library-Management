package com.example.librarymanagement.dto;

import com.example.librarymanagement.model.Subscription;

public record SubscriptionDto(String title, Double amount, Integer numberOfDays) {

    public Subscription toSubscription(){
        return new Subscription(title, amount, numberOfDays);
    }
}