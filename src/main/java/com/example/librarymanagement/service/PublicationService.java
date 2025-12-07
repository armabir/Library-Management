package com.example.librarymanagement.service;

import com.example.librarymanagement.interfaces.PublicationServiceInterface;
import com.example.librarymanagement.model.Publication;
import com.example.librarymanagement.model.User;
import com.example.librarymanagement.model.Vendor;
import com.example.librarymanagement.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PublicationService implements PublicationServiceInterface {

    private final PublicationRepository repository;

    public PublicationService(PublicationRepository repository) {
        this.repository = repository;
    }

    public List<Publication> getALL() {
        return repository.findAll();
    }

    public Publication save(Publication publication){
        Optional<Publication> optional = repository.findById(publication.getName());
        if (optional.isPresent()){
            System.out.println("Publication already Exist");
            return null;
        }
        repository.save(publication);
        return publication;
    }

    public Publication getByName(String name){
        return repository.findById(name).orElse(null);
    }

    public void delete(String name){
        repository.deleteById(name);
    }

    public void update(Publication publication){
        repository.save(publication);
    }
}
