package com.example.mils.demo.web.user;

import java.time.LocalDateTime;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    public String registerUser(@Validated UserRegisterForm form, BindingResult bindingResult,
            Model model) {
        
        try (InputStream uploadStream = form.getProfileImg().getInputStream()){
			String filename = form.getProfileImg().getOriginalFilename();
			FileOutputStream fos = new FileOutputStream(new File("src\\main\\resources\\static\\profile\\" + filename));
			IOUtils.copyLarge(uploadStream, fos);
		} catch (IOException e) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(), "profileImg", "アップロード時にエラーが発生しました"));
		} catch (Throwable e){
            bindingResult.addError(new FieldError(bindingResult.getObjectName(), "profileImg", e.toString()));
        }
        if (bindingResult.hasErrors()) {
            return showRegistrationForm(form, model);
        }
        userService.create(form.getEmail(), form.getPassword(), UserEntity.DEFAULT_AUTHORITIES, "src\\main\\resources\\static\\profile\\" + form.getProfileImg().getOriginalFilename());
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
        userService.updateDelatedAt(userId, LocalDateTime.now());
        return "redirect:/logout";
    }

    @GetMapping("/profile")
    public String showDetail(@AuthenticationPrincipal UserDetails loginUser, Model model) {
        UserEntity user = userService.findByEmail(loginUser.getUsername()).get();
        model.addAttribute("user", user);
        model.addAttribute("loginUser", loginUser);
        return "users/detail";
    }
}
