package com.example.librarymanagement.interfaces;

import com.example.librarymanagement.model.Vendor;

import java.util.List;

public interface VendorServiceInterface {
    List<Vendor> getALL();
    Vendor save(Vendor vendor);
    boolean hasNoChild(String name);
    void delete(String name);
    void update(Vendor vendor);
}
