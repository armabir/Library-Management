package com.example.librarymanagement.interfaces;

import com.example.librarymanagement.model.Student;

import java.util.List;

public interface StudentServiceInterface {
    void toList();
    List<Student> getALL();
    Student save(Student student);
    Student getByEmail(String email);
    boolean exists(String email);
    void delete(String email);
    void update(Student student);
}
