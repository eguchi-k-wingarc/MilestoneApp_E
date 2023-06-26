package com.example.mils.demo.domain.taskLabel;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TaskLabelEntity {
    private long id;
    private Long taskId;  // TODO: nullを許可しないlong型に変更する
    private Long labelId;  // TODO: nullを許可しないlong型に変更する
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
