package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dto.BookDto;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Publication;
import com.example.librarymanagement.service.BookService;
import com.example.librarymanagement.service.PublicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final PublicationService publicationService;
    public BookController(BookService bookService, PublicationService publicationService) {
        this.bookService = bookService;
        this.publicationService = publicationService;
    }

    @GetMapping("book")
    public String getBook(Model model){
        model.addAttribute("dto", new BookDto("", "", null, ""));

        List<Book> bookList = bookService.getALL();
        model.addAttribute("books", bookList);

        List<Publication> publisherList = publicationService.getALL();
        model.addAttribute("publishers", publisherList);

        return "book";
    }

    @PostMapping("book")
    public String addBook(@ModelAttribute BookDto dto){
        System.out.println("Test: " + dto);

        Book book = bookService.save(dto.toBook());
        if(book == null){
            return "redirect:book?book=failed";
        } else {
            System.out.println("Added new book");
            return "redirect:book";
        }
    }

    @PostMapping("book/{bookName}/delete")
    public String deleteBook(@PathVariable String bookName){
        if(bookService.hasNoChild(bookName)){
            bookService.delete(bookName);
            return "redirect:/book";
        }
        else if(bookService.hasNoPurchase(bookName)){
            bookService.delete(bookName);
            return "redirect:/book";
        }
        return "redirect:/book?child=true";
    }

    @PostMapping("book/update")
    public String updateQuantity(@ModelAttribute BookDto dto){

        System.out.println(dto);
        bookService.update(dto.toBook());
        return "redirect:/book";
    }
}