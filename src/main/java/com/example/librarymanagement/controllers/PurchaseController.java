package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dto.PurchaseDto;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Purchase;
import com.example.librarymanagement.model.Vendor;
import com.example.librarymanagement.service.BookService;
import com.example.librarymanagement.service.PurchaseService;
import com.example.librarymanagement.service.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final BookService bookService;
    private final VendorService vendorService;

    public PurchaseController(PurchaseService purchaseService, BookService bookService, VendorService vendorService) {
        this.purchaseService = purchaseService;
        this.bookService = bookService;
        this.vendorService = vendorService;
    }

    @GetMapping("purchase")
    public String getPurchase(Model model){
        model.addAttribute("dto", new PurchaseDto(null, null, 0, 0.0, ""));

        List<Purchase> purchaseList = purchaseService.getALL();
        model.addAttribute("purchases", purchaseList);

        List<Book> bookList = bookService.getALL();
        model.addAttribute("books", bookList);

        List<Vendor> vendorList = vendorService.getALL();
        model.addAttribute("vendors", vendorList);

        return "purchase";
    }

    @PostMapping("purchase")
    public String addPurchase(@ModelAttribute PurchaseDto dto){
        System.out.println("Test: " + dto);

        purchaseService.save(dto.toPurchase());
        return "redirect:purchase";
    }

    @PostMapping("purchase/{id}/delete")
    public String deletePurchase(@PathVariable Integer id){
        purchaseService.delete(id);
        return "redirect:/purchase";
    }

//    @PostMapping("purchase/update")
//    public String updatePurchase(@ModelAttribute PurchaseDto dto){
//
//        purchaseService.update(dto.toPurchase());
//        return "redirect:/purchase";
//    }
}