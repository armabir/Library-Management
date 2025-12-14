package com.example.librarymanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "purchases")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bookName;
    private String vendor;
    private int quantity;
    private Double perBookPrice;
    private Double totalAmount;
    private LocalDate purchaseDate;

    public Purchase(String bookName, String vendor, int quantity, Double perBookPrice, Double totalAmount, LocalDate purchaseDate) {
        this.bookName = bookName;
        this.vendor = vendor;
        this.quantity = quantity;
        this.perBookPrice = perBookPrice;
        this.totalAmount = totalAmount;
        this.purchaseDate = purchaseDate;
    }
}