package com.example.librarymanagement.dto;

import com.example.librarymanagement.model.Publication;

public record PublicationDto(String name, String address, String description) {

    public Publication toPublication(){
        return new Publication(name, address, description);
    }
}
