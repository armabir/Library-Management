package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dto.VendorDto;
import com.example.librarymanagement.model.Vendor;
import com.example.librarymanagement.service.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VendorController {

    private final VendorService service;

    public VendorController(VendorService service) {
        this.service = service;
    }

    @GetMapping("vendor")
    public String getVendor(Model model){
        model.addAttribute("dto", new VendorDto("", "", ""));
        System.out.println("check dash");

        List<Vendor> vendorList = service.getALL();
        model.addAttribute("vendors", vendorList);

        return "vendor";
    }

    @PostMapping("vendor")
    public String addVendor(@ModelAttribute VendorDto dto){
        System.out.println("Test: " + dto);
        System.out.println("check add");

        Vendor vendor = service.save(dto.toVendor());
        if(vendor == null){
            return "redirect:vendor?vendor=failed";
        } else {
            System.out.println("Added new vendor");
            return "redirect:vendor";
        }
    }

    @PostMapping("vendor/{name}/delete")
    public String deleteVendor(@PathVariable String name){
        if(service.hasNoChild(name)){
            service.delete(name);
            return "redirect:/vendor";
        }
        return "redirect:/vendor?child=true";
    }

    @PostMapping("vendor/update")
    public String updateVendor(@ModelAttribute VendorDto dto){
        System.out.println("check Update");

        service.update(dto.toVendor());
        return "redirect:/vendor";
    }
}