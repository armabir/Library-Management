package com.example.librarymanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "allotments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Allotment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bookName;
    private String studentEmail;
    private String subscriptionType;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate allotmentDate;

    public Allotment(String bookName, String studentEmail, String subscriptionType, LocalDate startDate, LocalDate endDate, LocalDate allotmentDate) {
        this.bookName = bookName;
        this.studentEmail = studentEmail;
        this.subscriptionType = subscriptionType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.allotmentDate = allotmentDate;
    }
}