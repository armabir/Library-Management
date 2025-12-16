package com.example.librarymanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "purchases")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private Double perBookPrice;
    private Double totalAmount;
    private LocalDate purchaseDate;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Purchase(Book book, Vendor vendor, int quantity, Double perBookPrice, Double totalAmount, LocalDate purchaseDate) {
        this.book = book;
        this.vendor = vendor;
        this.quantity = quantity;
        this.perBookPrice = perBookPrice;
        this.totalAmount = totalAmount;
        this.purchaseDate = purchaseDate;
    }
}