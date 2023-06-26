package com.example.mils.demo.web.label;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LabelUpdateForm {
    private Long id;

    @NotBlank
    @Size(max=256)
    private String name;

    @Size(max=256)
    private String color;
}
