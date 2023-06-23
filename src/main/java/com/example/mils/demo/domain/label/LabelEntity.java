package com.example.mils.demo.domain.label;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LabelEntity {
    private long id;
    private String name;
    private String color;
}
