package com.example.librarymanagement.interfaces;

import com.example.librarymanagement.model.Publication;

import java.util.List;

public interface PublicationServiceInterface {
    void toList();
    List<Publication> getALL();
    Publication save(Publication publication);
    Publication getByName(String name);
    void delete(String name);
    void update(Publication publication);
}
