package com.example.librarymanagement.dto;

import com.example.librarymanagement.model.Allotment;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record AllotmentDto(String bookName, String studentEmail, String subscriptionType, String startDate, String endDate) {

    public Allotment toAllotment(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return new Allotment(null, bookName, studentEmail, subscriptionType, start, end, LocalDate.now());
    }
}