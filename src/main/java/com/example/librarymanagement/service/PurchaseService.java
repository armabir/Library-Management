package com.example.librarymanagement.service;

import com.example.librarymanagement.interfaces.PublicationServiceInterface;
import com.example.librarymanagement.interfaces.PurchaseServiceInterface;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Publication;
import com.example.librarymanagement.model.Purchase;
import com.example.librarymanagement.model.Student;
import com.example.librarymanagement.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService implements PurchaseServiceInterface {

    private final PurchaseRepository repository;
    private final BookService bookService;

    public PurchaseService(PurchaseRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    public List<Purchase> getALL() {
        return repository.findAll();
    }

    public Purchase save(Purchase purchase){
        Optional<Purchase> optional = repository.findById(purchase.getId());
        if (optional.isPresent()){
            System.out.println("Purchase already Exist");
            return null;
        }
        repository.save(purchase);
        bookService.updateQuantity(purchase.getBook(), purchase.getQuantity());
        /// can we reduce this code with hibernate??
        /// purchase.getBook().setAvailableQuantity(purchase.getBook().getAvailableQuantity() + purchase.getQuantity());
        return purchase;
    }

    //// Best Example for using Hibernate!!
    public boolean hasEnoughBook(int id){
        Purchase purchase = repository.findById(id).orElse(null);

        // Best Example for using Hibernate!!
        return purchase.getQuantity() <= purchase.getBook().getAvailableQuantity();
    }

    public void delete(Integer id){
        Purchase purchase = repository.findById(id).orElse(null);

        /// same here, use Hibernate Relation??
        bookService.updateQuantity(purchase.getBook(), purchase.getQuantity()*(-1));
        repository.deleteById(id);
    }

    public void update(Purchase purchase){
        repository.save(purchase);
    }

}