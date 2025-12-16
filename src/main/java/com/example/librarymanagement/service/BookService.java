package com.example.librarymanagement.service;

import com.example.librarymanagement.interfaces.BookServiceInterface;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Publication;
import com.example.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements BookServiceInterface {

    @Autowired
    DataSource dataSource;

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getALL() {
        return repository.findAll();
    }

    public Book save(Book book){
        Optional<Book> optional = repository.findById(book.getBookName());
        if (optional.isPresent()){
            System.out.println("Book already Exist");
            return null;
        }
        repository.save(book);
        return book;
    }

    public void delete(String bookName){
        repository.deleteById(bookName);
    }

    public void update(Book book){
        repository.save(book);
    }

    // used for purchase, allotment, return
    public void updateQuantity(Book book, int quantity){
        book.setAvailableQuantity(book.getAvailableQuantity() + quantity);
        repository.save(book);
    }

}