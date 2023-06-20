package com.example.mils.demo.domain.milestone;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MilestoneEntity {
    private long id;
    private String title;
    private String description;
}
