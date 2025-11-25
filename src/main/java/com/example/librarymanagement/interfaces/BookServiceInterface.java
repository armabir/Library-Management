package com.example.librarymanagement.interfaces;

import com.example.librarymanagement.model.Book;

import java.util.List;

public interface BookServiceInterface {
    void toList();
    List<Book> getALL();
    Book save(Book book);
    Book getByBookName(String bookName);
    void delete(String bookName);
    void update(Book book);
    void updateQuantity(String bookName, Integer newQuantity);
    void decreaseQuantity(String bookName, Integer quantity);
    void increaseQuantity(String bookName, Integer quantity);
}
