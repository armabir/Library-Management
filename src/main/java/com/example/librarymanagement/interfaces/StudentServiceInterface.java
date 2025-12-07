package com.example.librarymanagement.interfaces;

import com.example.librarymanagement.model.Student;

import java.util.List;

public interface StudentServiceInterface {
    List<Student> getALL();
    Student save(Student student);
    void delete(String email);
    void update(Student student);
}
