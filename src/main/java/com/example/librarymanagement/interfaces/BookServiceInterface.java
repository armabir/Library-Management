package com.example.librarymanagement.interfaces;

import com.example.librarymanagement.model.Book;

import java.util.List;

public interface BookServiceInterface {
    List<Book> getALL();
    Book save(Book book);
    boolean hasNoChild(String name);
    boolean hasNoPurchase(String name);
    void delete(String bookName);
    void update(Book book);
    void updateQuantity(Book book, int quantity);
}
