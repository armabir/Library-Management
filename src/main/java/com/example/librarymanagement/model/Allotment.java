package com.example.librarymanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "allotments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Allotment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String studentEmail;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate allotmentDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscriptionType;

    public Allotment(Book book, String studentEmail, Subscription subscriptionType, LocalDate startDate, LocalDate endDate, LocalDate allotmentDate) {
        this.book = book;
        this.studentEmail = studentEmail;
        this.subscriptionType = subscriptionType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.allotmentDate = allotmentDate;
    }
}