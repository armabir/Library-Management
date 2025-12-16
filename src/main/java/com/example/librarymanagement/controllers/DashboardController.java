package com.example.librarymanagement.controllers;

import com.example.librarymanagement.model.Allotment;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Student;
import com.example.librarymanagement.model.Vendor;
import com.example.librarymanagement.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DashboardController {

    private final BookService bookService;
    private final StudentService studentService;
    private final VendorService vendorService;
    private final AllotmentService allotmentService;

    public DashboardController(BookService bookService, StudentService studentService,
                               VendorService vendorService, AllotmentService allotmentService) {
        this.bookService = bookService;
        this.studentService = studentService;
        this.vendorService = vendorService;
        this.allotmentService = allotmentService;
    }

    @GetMapping("/")
    public String getDashboard(Model model){

        List<Book> books = bookService.getALL();
        List<Student> students = studentService.getALL();
        List<Vendor> vendors = vendorService.getALL();
        List<Allotment> allotments = allotmentService.getALL();

        model.addAttribute("totalBooks", books.size());
        model.addAttribute("totalStudents", students.size());
        model.addAttribute("totalVendors", vendors.size());
        model.addAttribute("activeAllotments", allotments.size());

        int availableBooks = books.stream()
                .mapToInt(Book::getAvailableQuantity)
                .sum();

        model.addAttribute("availableBooks", availableBooks);
        model.addAttribute("allottedBooks", allotments.size());

        return "dashboard";
    }
}