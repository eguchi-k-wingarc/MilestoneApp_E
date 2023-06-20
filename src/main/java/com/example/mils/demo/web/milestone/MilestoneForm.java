package com.example.mils.demo.web.milestone;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MilestoneForm {
    @NotBlank(message = "タイトルを入力してください")
    @Size(max = 256, message = "多すぎです")
    private String title;

    @NotBlank(message = "説明を入力してください")
    @Size(max = 256, message = "多すぎよ")
    private String description;
}
