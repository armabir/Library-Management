package com.example.librarymanagement.dto;

import com.example.librarymanagement.model.Allotment;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Subscription;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record AllotmentDto(Book book, String studentEmail, Subscription subscriptionType, String startDate, String endDate) {

    public Allotment toAllotment(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return new Allotment(book, studentEmail, subscriptionType, start, end, LocalDate.now());
    }
}