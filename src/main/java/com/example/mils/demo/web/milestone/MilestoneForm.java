package com.example.mils.demo.web.milestone;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MilestoneForm {
    @NotBlank(message = "マイルストーン入力しろ!")
    @Size(max = 256)
    private String milestone;
    @NotBlank(message = "説明入力しろ!")
    @Size(max = 256)
    private String description;
}
