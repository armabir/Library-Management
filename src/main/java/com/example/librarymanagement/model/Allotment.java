package com.example.librarymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Allotment {
    private Integer id;
    private String bookName;
    private String studentEmail;
    private String subscriptionType;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate allotmentDate;
}