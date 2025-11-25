package com.example.librarymanagement.dto;

import com.example.librarymanagement.model.Book;

public record BookDto(String bookName, String authorName, String publisherName, String bookImage) {

    public Book toBook(){
        return new Book(bookName, bookImage, authorName, publisherName, 0);
    }
}