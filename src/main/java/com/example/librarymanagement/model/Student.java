package com.example.librarymanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    @Id
    private String email;
    private String phone;
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "student")
    private List<Allotment> allotmentList;

    public Student(String name, String email, String phone, LocalDate registrationDate) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.registrationDate = registrationDate;
    }
}