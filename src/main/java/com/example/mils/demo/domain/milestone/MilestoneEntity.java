package com.example.mils.demo.domain.milestone;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MilestoneEntity {
    private long id;
    private long userId;
    private String name;
    private String description;
    private boolean isComplete;
    private int progress;
    private String deadline;
    private String createdAt;
    private String updatedAt;
}
