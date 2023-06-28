package com.example.mils.demo.web.user;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mils.demo.domain.user.UserEntity;
import com.example.mils.demo.domain.user.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @GetMapping
    public String showList(@AuthenticationPrincipal UserDetails loginUser, Model model){
        List<UserEntity> usersList = userService.findAll();
        model.addAttribute("usersList", usersList);
        model.addAttribute("loginUser", loginUser);
        return "users/list";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(@ModelAttribute UserRegisterForm form, Model model) {
        return "users/register";
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

    @GetMapping("/update")
    public String showUpdateForm(UserRegisterForm form, Model model, @AuthenticationPrincipal UserDetails loginUser){
        UserEntity user = userService.findByEmail(loginUser.getUsername()).get();
        if (user != null) {
            form.setEmail(user.getEmail());
            form.setPassword(user.getPassword());
        } else {
            return "redirect:/users";
        }
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("userUpdateForm", form);
        return "users/updateForm";
    }

    @PostMapping("/update")
    public String updateUser(@Validated UserRegisterForm form, BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails loginUser) {
         if (bindingResult.hasErrors()) {
            return showUpdateForm(form, model, loginUser);
        }
        long userId = userService.findByEmail(loginUser.getUsername()).get().getId();
        userService.update(userId, form.getEmail(), form.getPassword());
        return "redirect:/profile";
    }

    @PostMapping("/delete")
    public String deleteUser(@Validated UserRegisterForm form, BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails loginUser) {
         if (bindingResult.hasErrors()) {
            return showUpdateForm(form, model, loginUser);
        }
        long userId = userService.findByEmail(loginUser.getUsername()).get().getId();
        userService.update(userId, form.getEmail(), form.getPassword());
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String showDetail(@AuthenticationPrincipal UserDetails loginUser, Model model) {
        UserEntity user = userService.findByEmail(loginUser.getUsername()).get();
        model.addAttribute("user", user);
        model.addAttribute("loginUser", loginUser);
        return "users/detail";
    }
}
