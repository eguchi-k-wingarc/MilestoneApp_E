package com.example.mils.demo.web.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class UserRegisterForm {
    @NotBlank
    @Size(max = 256)
    private String email;

    @NotBlank
    @Size(max = 256)
    private String password;

    @NotNull
    private String authorities;
}
