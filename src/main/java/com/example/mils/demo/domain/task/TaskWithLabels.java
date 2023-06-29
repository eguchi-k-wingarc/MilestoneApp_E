package com.example.mils.demo.domain.task;

import java.util.List;
import com.example.mils.demo.domain.label.LabelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Taskに紐づく複数のラベルを保持するクラス
 */
@AllArgsConstructor
@Data
public class TaskWithLabels {
    private TaskEntity task;
    private List<LabelEntity> labels;
}
