package com.example.mils.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mils.demo.domain.user.UserEntity;
import com.example.mils.demo.domain.user.UserService;
import com.example.mils.demo.web.user.UserRegisterForm;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class IndexController {

    private UserService userService;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(@ModelAttribute UserRegisterForm form, Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Validated UserRegisterForm form, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return showRegistrationForm(form, model);
        }
        userService.create(form.getEmail(), form.getPassword(), UserEntity.DEFAULT_AUTHORITIES);
        return "redirect:/login";
    }

    @GetMapping("/admin-dashboard")
    public String showAdminDashboardForm() {
        return "admin-dashboard";
    }

}
