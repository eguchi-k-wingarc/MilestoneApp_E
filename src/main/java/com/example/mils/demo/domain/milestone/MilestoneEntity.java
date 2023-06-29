package com.example.mils.demo.domain.milestone;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MilestoneEntity {
    private long id;
    private Long userId; // TODO: nullを許可しないlong型に変更する
    private String name;
    private String description;
    private int progress;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
