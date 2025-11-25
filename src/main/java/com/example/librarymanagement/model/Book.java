package com.example.librarymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String bookName;
    private String bookImage;
    private String authorName;
    private String publisherName;
    private Integer availableQuantity;
}