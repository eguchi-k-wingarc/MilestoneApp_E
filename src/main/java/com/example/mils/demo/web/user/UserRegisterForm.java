package com.example.mils.demo.web.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserRegisterForm {

    @NotBlank
    @Size(max = 256)
    private String name;

    @NotBlank
    @Size(max = 256)
    private String email;

    @NotBlank
    @Size(max = 256)
    private String password;

    private MultipartFile profileImg;

}
