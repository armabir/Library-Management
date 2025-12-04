package com.example.librarymanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vendors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
    @Id
    private String name;
    private String email;
    private String phone;

}