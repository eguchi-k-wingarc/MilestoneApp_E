package com.example.mils.demo.domain.task;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TaskEntity {
    private long id;
    private long userId;
    private long milestoneId;
    private String name;
    private String description;
    private boolean isComplete;
    private String deadline;
    private String createdAt;
    private String updatedAt;
}
