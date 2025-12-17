package com.example.librarymanagement.service;

import com.example.librarymanagement.interfaces.AllotmentServiceInterface;
import com.example.librarymanagement.model.Allotment;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.repository.AllotmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AllotmentService implements AllotmentServiceInterface {

    private final AllotmentRepository repository;
    private final BookService bookService;

    public AllotmentService(AllotmentRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    public List<Allotment> getALL() {
        return repository.findAll();
    }

    public Allotment save(Allotment allotment){
        Optional<Allotment> optional = repository.findById(allotment.getId());
        if (optional.isPresent()){
            System.out.println("Allotment already Exist");
            return null;
        }
        repository.save(allotment);
        bookService.updateQuantity(allotment.getBook(), -1);

        //// Is this better??
//        allotment.getBook().setAvailableQuantity(allotment.getBook().getAvailableQuantity() - 1);
        return allotment;
    }

    public void delete(Integer id){
        Allotment allotment = repository.findById(id).orElse(null);
        bookService.updateQuantity(allotment.getBook(), 1);

        //// Is this better??
//        allotment.getBook().setAvailableQuantity(allotment.getBook().getAvailableQuantity() + 1);
        repository.deleteById(id);
    }
}