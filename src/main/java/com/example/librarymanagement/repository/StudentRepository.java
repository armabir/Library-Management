package com.example.librarymanagement.repository;

import com.example.librarymanagement.model.Publication;
import com.example.librarymanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
