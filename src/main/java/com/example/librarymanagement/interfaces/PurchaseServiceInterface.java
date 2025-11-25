package com.example.librarymanagement.interfaces;

import com.example.librarymanagement.model.Purchase;

import java.util.List;

public interface PurchaseServiceInterface {
    void toList();
    List<Purchase> getALL();
    Purchase save(Purchase purchase);
    void delete(Integer id);
    void update(Purchase purchase);
}
