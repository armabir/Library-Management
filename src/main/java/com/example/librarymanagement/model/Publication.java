package com.example.librarymanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "publications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publication {
    @Id
    private String name;
    private String address;
    private String description;

    @OneToMany(mappedBy = "publisherName")
    private List<Book> bookList;

    public Publication(String description, String address, String name) {
        this.description = description;
        this.address = address;
        this.name = name;
    }
}
