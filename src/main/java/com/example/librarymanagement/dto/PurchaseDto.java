package com.example.librarymanagement.dto;

import com.example.librarymanagement.model.Purchase;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record PurchaseDto(String bookName, String vendor, Integer quantity, Double perBookPrice, String purchaseDate) {

    public Purchase toPurchase(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(purchaseDate, formatter);
        Double totalAmount = quantity * perBookPrice;
        return new Purchase(null, bookName, vendor, quantity, perBookPrice, totalAmount, date);
    }
}