package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dto.StudentDto;
import com.example.librarymanagement.model.Student;
import com.example.librarymanagement.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    private final StudentService service;
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("student")
    public String getStudent(Model model){
        model.addAttribute("dto", new StudentDto("", "", ""));

        List<Student> studentList = service.getALL();
        model.addAttribute("students", studentList);

        return "student";
    }

    @PostMapping("student")
    public String addStudent(@ModelAttribute StudentDto dto){
        System.out.println("Test: " + dto);

        Student student = service.save(dto.toStudent());
        if(student == null){
            return "redirect:student?student=failed";
        } else {
            System.out.println("Added new student");
            return "redirect:student";
        }
    }

    @PostMapping("student/{email}/delete")
    public String deleteStudent(@PathVariable String email){

        service.delete(email);
        return "redirect:/student";
    }

    @PostMapping("student/update")
    public String updateStudent(@ModelAttribute StudentDto dto){

        service.update(dto.toStudent());
        return "redirect:/student";
    }
}