package com.example.librarymanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private String bookName;
    private String bookImage;
    private String authorName;
    private Integer availableQuantity;

    @ManyToOne
    @JoinColumn(name = "publication_name")
    private Publication publisherName;

    @OneToMany(mappedBy = "book")
    private List<Purchase> purchaseList;

    @OneToMany(mappedBy = "book")
    private List<Allotment> allotmentList;

    public Book(String bookName, String bookImage, String authorName, Integer availableQuantity, Publication publisherName) {
        this.bookName = bookName;
        this.bookImage = bookImage;
        this.authorName = authorName;
        this.availableQuantity = availableQuantity;
        this.publisherName = publisherName;
    }
}