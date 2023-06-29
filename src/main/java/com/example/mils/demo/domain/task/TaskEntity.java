package com.example.mils.demo.domain.task;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TaskEntity {
    private long id;
    private Long userId; // TODO: nullを許可しないlong型に変更する
    private Long milestoneId; // TODO: nullを許可しないlong型に変更する
    private String name;
    private String description;
    private boolean isComplete;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
