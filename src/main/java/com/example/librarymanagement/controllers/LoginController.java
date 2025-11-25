package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dto.LoginDto;
import com.example.librarymanagement.model.User;
import com.example.librarymanagement.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final UserService service;

    public LoginController(UserService service) {
        this.service = service;
    }

    @GetMapping("login")
    public String getLogin(Model model, HttpSession session){
        String name = (String) session.getAttribute("name");
        if (name != null){
            model.addAttribute("name", name);
            return "redirect:/";
        } else {
            model.addAttribute("dto", new LoginDto("", ""));
            return "login";
        }
    }

    @PostMapping("login")
    public String login(@ModelAttribute LoginDto dto, HttpSession session){
        System.out.println("Login: " + dto);

        if(service.exists(dto.email(), dto.pass())){
            User user = service.getByEmail(dto.email());
            session.setAttribute("name", user.getName());
            return "redirect:/";
        } else {
            return "redirect:login?login=failed";
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:login?logout=true";
    }
}
