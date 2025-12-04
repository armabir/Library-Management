package com.example.librarymanagement.interfaces;

import com.example.librarymanagement.model.Publication;

import java.util.List;

public interface PublicationServiceInterface {
    List<Publication> getALL();
    Publication save(Publication publication);
    void delete(String name);
    void update(Publication publication);
}
