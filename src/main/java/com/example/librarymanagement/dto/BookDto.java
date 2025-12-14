package com.example.librarymanagement.dto;

import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Publication;

public record BookDto(String bookName, String authorName, Publication publisherName, String bookImage) {

    public Book toBook(){
        return new Book(bookName, bookImage, authorName, 0, publisherName);
    }
}