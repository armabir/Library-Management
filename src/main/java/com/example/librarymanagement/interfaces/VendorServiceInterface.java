package com.example.librarymanagement.interfaces;

import com.example.librarymanagement.model.Vendor;

import java.util.List;

public interface VendorServiceInterface {
    void toList();
    List<Vendor> getALL();
    Vendor save(Vendor vendor);
    Vendor getByName(String name);
    boolean exists(String name);
    void delete(String name);
    void update(Vendor vendor);
}
