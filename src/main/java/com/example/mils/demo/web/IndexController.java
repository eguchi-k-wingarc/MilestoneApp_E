package com.example.mils.demo.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class IndexController {


    @GetMapping
    public String index(Model model,@AuthenticationPrincipal UserDetails loginUser) {
        model.addAttribute("loginUser", loginUser);
        return "index";
    }

    @GetMapping("/login")
    public String redirectLogin(){
        return "redirect:users/login";
    }
    @GetMapping("/register")
    public String redirectRegister(){
        return "redirect:users/register";
    }
}
