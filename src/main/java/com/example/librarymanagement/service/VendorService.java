package com.example.librarymanagement.service;

import com.example.librarymanagement.interfaces.VendorServiceInterface;
import com.example.librarymanagement.model.Publication;
import com.example.librarymanagement.model.Vendor;
import com.example.librarymanagement.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService implements VendorServiceInterface {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public List<Vendor> getALL() {
        return vendorRepository.findAll();
    }

    public Vendor save(Vendor vendor){
        Optional<Vendor> optional = vendorRepository.findById(vendor.getName());
        if (optional.isPresent()){
            System.out.println("Vendor already Exist");
            return null;
        }
        vendorRepository.save(vendor);
        return vendor;
    }

    public boolean hasNoChild(String name){
        Vendor vendor = vendorRepository.findById(name).orElse(null);
        return vendor.getPurchaseList().isEmpty();
    }

    public void delete(String name){
        vendorRepository.deleteById(name);
    }

    public void update(Vendor vendor){
        vendorRepository.save(vendor);
    }
}