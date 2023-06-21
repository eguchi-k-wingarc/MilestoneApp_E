package com.example.mils.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/login2")
    public String showLoginForm() {
        return "login";
    }
}
