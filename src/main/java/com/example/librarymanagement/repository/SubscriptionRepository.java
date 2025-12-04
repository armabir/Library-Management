package com.example.librarymanagement.repository;

import com.example.librarymanagement.model.Subscription;
import com.example.librarymanagement.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
}
