package com.example.mils.demo.web.label;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LabelUpdateForm {
    private Long id; // NOTE: インスタンス生成時のみNullを許容するためlong型ではなく、Long型にしている

    @NotBlank
    @Size(max=256)
    private String name;

    @Size(max=256)
    private String color;
}
