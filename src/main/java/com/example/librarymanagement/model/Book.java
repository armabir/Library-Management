package com.example.librarymanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
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

}