package com.example.librarymanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    @Id
    private String title;
    private Double amount;
    private Integer numberOfDays;

    @OneToMany(mappedBy = "subscriptionType")
    private List<Allotment> allotmentList;

    public Subscription(String title, Double amount, Integer numberOfDays) {
        this.title = title;
        this.amount = amount;
        this.numberOfDays = numberOfDays;
    }
}