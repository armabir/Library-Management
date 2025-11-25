package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dto.PublicationDto;
import com.example.librarymanagement.model.Publication;
import com.example.librarymanagement.model.User;
import com.example.librarymanagement.service.PublicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PublicationController {

    private final PublicationService service;
    public PublicationController(PublicationService service) {
        this.service = service;
    }

    @GetMapping("publication")
    public String getPublication(Model model){
        model.addAttribute("dto", new PublicationDto("", "", ""));

        List<Publication> publicationList = service.getALL();
        model.addAttribute("publications", publicationList);

        return "publication";
    }

    @PostMapping("publication")
    public String addPublication(@ModelAttribute PublicationDto dto){
        System.out.println("Test: " + dto);

        Publication publication = service.save(dto.toPublication());
        if(publication == null){
            return "redirect:publication?publication=failed";
        } else {
            System.out.println("Added new pub");
            return "redirect:publication";
        }
    }

    @PostMapping("publication/{name}")
    public String deletePublication(@PathVariable String name){
        service.delete(name);
        return "redirect:/publication";
    }

    @PostMapping("publication/update")
    public String updatePublication(@ModelAttribute PublicationDto dto){

        service.update(dto.toPublication());
        return "redirect:/publication";
    }
}
