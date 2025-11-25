package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dto.LoginDto;
import com.example.librarymanagement.dto.SignupDto;
import com.example.librarymanagement.model.User;
import com.example.librarymanagement.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    private final UserService service;

    public SignupController(UserService service) {
        this.service = service;
    }

    @GetMapping("signup")
    public String getSignup(Model model, HttpSession session){
        String name = (String) session.getAttribute("name");
        if (name != null){
            model.addAttribute("name", name);
            return "redirect:/";
        } else {
            model.addAttribute("dto", new SignupDto("", "", ""));
            return "signup";
        }
    }

    @PostMapping("signup")
    public String signup(@ModelAttribute SignupDto dto){
        System.out.println("signup: " + dto);

        User user = service.saveUser(dto.toUser());
        if(user == null){
            return "redirect:signup?signup=failed";
        } else {
            return "redirect:login?signup=true";
        }
    }
}
