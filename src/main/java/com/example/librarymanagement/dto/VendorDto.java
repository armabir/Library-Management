package com.example.librarymanagement.dto;

import com.example.librarymanagement.model.Vendor;

public record VendorDto(String name, String email, String phone) {

    public Vendor toVendor(){
        return new Vendor(name, email, phone);
    }
}