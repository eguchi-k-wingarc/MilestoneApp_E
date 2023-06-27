package com.example.mils.demo.domain.task;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;

import com.example.mils.demo.domain.label.LabelEntity;

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

    @OneToMany(mappedBy = "task")
    private Set<LabelEntity> labels = new HashSet<>();

    public Set<LabelEntity> getLabels() {
        return this.labels;
    }
}
