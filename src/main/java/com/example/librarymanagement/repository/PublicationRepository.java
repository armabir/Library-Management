package com.example.librarymanagement.repository;

import com.example.librarymanagement.model.Publication;
import com.example.librarymanagement.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, String> {
}
