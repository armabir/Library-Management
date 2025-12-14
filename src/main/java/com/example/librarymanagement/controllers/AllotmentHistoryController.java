package com.example.librarymanagement.controllers;

import com.example.librarymanagement.model.Allotment;
import com.example.librarymanagement.service.AllotmentService;
import com.example.librarymanagement.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AllotmentHistoryController {

    private final AllotmentService allotmentService;
    private final BookService bookService;

    public AllotmentHistoryController(AllotmentService allotmentService, BookService bookService) {
        this.allotmentService = allotmentService;
        this.bookService = bookService;
    }

    @GetMapping("allotment-history")
    public String getAllotmentHistory(Model model){

        List<Allotment> allotmentList = allotmentService.getALL();
        model.addAttribute("allotments", allotmentList);

        return "allotment-history";
    }

    @PostMapping("allotment-history/{id}/delete")
    public String deleteAllotment(@PathVariable Integer id){

        allotmentService.delete(id);

        return "redirect:/allotment-history?success=true";
    }
}