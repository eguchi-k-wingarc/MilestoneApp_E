package com.example.mils.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping()
    public String index(Model model) {
        String view = "index";
        model.addAttribute("model", "aa");
        return view;
    }
}
