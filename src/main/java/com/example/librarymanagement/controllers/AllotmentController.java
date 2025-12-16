package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dto.AllotmentDto;
import com.example.librarymanagement.model.Allotment;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Student;
import com.example.librarymanagement.model.Subscription;
import com.example.librarymanagement.service.AllotmentService;
import com.example.librarymanagement.service.BookService;
import com.example.librarymanagement.service.StudentService;
import com.example.librarymanagement.service.SubscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AllotmentController {

    private final AllotmentService allotmentService;
    private final BookService bookService;
    private final StudentService studentService;
    private final SubscriptionService subscriptionService;

    public AllotmentController(AllotmentService allotmentService, BookService bookService,
                               StudentService studentService, SubscriptionService subscriptionService) {
        this.allotmentService = allotmentService;
        this.bookService = bookService;
        this.studentService = studentService;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("allotment")
    public String getAllotment(Model model){
        model.addAttribute("dto", new AllotmentDto(null, null, null, "", ""));

        List<Book> bookList = bookService.getALL();
        model.addAttribute("books", bookList);

        List<Student> studentList = studentService.getALL();
        model.addAttribute("students", studentList);

        List<Subscription> subscriptionList = subscriptionService.getALL();
        model.addAttribute("subscriptions", subscriptionList);

        return "allotment";
    }

    @PostMapping("allotment")
    public String addAllotment(@ModelAttribute AllotmentDto dto){
        System.out.println("Test: " + dto);

        allotmentService.save(dto.toAllotment());
        return "redirect:allotment?success=true";
    }
}