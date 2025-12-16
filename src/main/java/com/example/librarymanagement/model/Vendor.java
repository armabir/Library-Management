package com.example.librarymanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "vendors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
    @Id
    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "vendor")
    private List<Purchase> purchaseList;

    public Vendor(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}