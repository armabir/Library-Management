package com.example.librarymanagement.repository;

import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
