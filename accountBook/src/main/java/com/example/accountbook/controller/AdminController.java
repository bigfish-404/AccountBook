package com.example.accountbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String redirectToLogin(){
        return "redirect:http://localhost:5173/admin";
    }
}
