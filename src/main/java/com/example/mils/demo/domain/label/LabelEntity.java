package com.example.mils.demo.domain.label;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;

import com.example.mils.demo.domain.taskLabel.TaskLabelEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LabelEntity {
    private long id;
    private String name;
    private String color;

    @OneToMany(mappedBy = "task")
    private Set<TaskLabelEntity> taskLabels = new HashSet<>();
}
