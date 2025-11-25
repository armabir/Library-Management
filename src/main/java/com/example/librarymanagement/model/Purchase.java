package com.example.librarymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    private Integer id;
    private String bookName;
    private String vendor;
    private Integer quantity;
    private Double perBookPrice;
    private Double totalAmount;
    private LocalDate purchaseDate;
}