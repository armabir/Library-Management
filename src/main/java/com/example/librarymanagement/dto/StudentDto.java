package com.example.librarymanagement.dto;

import com.example.librarymanagement.model.Student;
import java.time.LocalDate;

public record StudentDto(String name, String email, String phone) {

    public Student toStudent(){
        return new Student(name, email, phone, LocalDate.now());
    }
}