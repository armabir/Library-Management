package com.example.librarymanagement.service;

import com.example.librarymanagement.interfaces.StudentServiceInterface;
import com.example.librarymanagement.model.Publication;
import com.example.librarymanagement.model.Student;
import com.example.librarymanagement.model.Vendor;
import com.example.librarymanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements StudentServiceInterface {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getALL() {
        return repository.findAll();
    }

    public Student save(Student student){
        Optional<Student> optional = repository.findById(student.getName());
        if (optional.isPresent()){
            System.out.println("Student already Exist");
            return null;
        }
        repository.save(student);
        return student;
    }

    public boolean hasNoChild(String email){
        Student student = repository.findById(email).orElse(null);
        return student.getAllotmentList().isEmpty();
    }

    public void delete(String email){
        repository.deleteById(email);
    }

    public void update(Student student){
        repository.save(student);
    }
}