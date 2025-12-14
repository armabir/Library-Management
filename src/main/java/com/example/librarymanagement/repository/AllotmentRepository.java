package com.example.librarymanagement.repository;

import com.example.librarymanagement.model.Allotment;
import com.example.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllotmentRepository extends JpaRepository<Allotment, Integer> {
}
