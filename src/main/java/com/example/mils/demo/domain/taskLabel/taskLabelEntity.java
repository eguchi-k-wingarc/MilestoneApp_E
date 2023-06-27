package com.example.mils.demo.domain.taskLabel;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import com.example.mils.demo.domain.label.LabelEntity;
import com.example.mils.demo.domain.task.TaskEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Table(name = "taskLabels")
public class TaskLabelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // private Long taskId;  // TODO: nullを許可しないlong型に変更する
    // private Long labelId;  // TODO: nullを許可しないlong型に変更する

    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;

    @ManyToOne
    @JoinColumn(name = "label_id")
    private LabelEntity label;
}
