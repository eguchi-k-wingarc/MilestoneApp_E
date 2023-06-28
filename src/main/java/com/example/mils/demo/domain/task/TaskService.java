package com.example.mils.demo.domain.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.mils.demo.domain.DeathLevelOperator;
import com.example.mils.demo.domain.label.LabelEntity;
import com.example.mils.demo.domain.label.LabelRepository;
import com.example.mils.demo.domain.milestone.MilestoneRepository;
import com.example.mils.demo.domain.taskLabel.TaskLabelEntity;
import com.example.mils.demo.domain.taskLabel.TaskLabelRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final LabelRepository labelRepository;
    private final TaskLabelRepository taskLabelRepository;
    private final MilestoneRepository milestoneRepository;
    private final DeathLevelOperator deathLevelOperator;

    public List<TaskEntity> findAll() {
        List<TaskEntity> list = taskRepository.findAll();
        List<TaskEntity> processedList = list.stream().map(task -> {
            task.setDeadline(deathLevelOperator.adjustDeadline(task.getDeadline()));
            return task;
        }).collect(Collectors.toList());
        return processedList;
    }

    public TaskEntity findById(long id) {
        TaskEntity task = taskRepository.findById(id);
        task.setDeadline(deathLevelOperator.adjustDeadline(task.getDeadline()));
        return task;
    }

    public List<TaskEntity> findByMilestoneId(Long milestoneId) {
        List<TaskEntity> list = taskRepository.findByMilestoneId(milestoneId);
        List<TaskEntity> processedList = list.stream().map(task -> {
            task.setDeadline(deathLevelOperator.adjustDeadline(task.getDeadline()));
            return task;
        }).collect(Collectors.toList());
        return processedList;
    }

    @Transactional
    public void create(long milestoneId, String name, String description, LocalDateTime deadline) {
        taskRepository.create(milestoneId, name, description, deadline);
    }

    @Transactional
    public void update(long id, String name, String description, LocalDateTime deadline) {
        taskRepository.update(id, name, description, deadline);
    }

    @Transactional
    public void updateIsComplete(long id, Boolean isComplete) {
        taskRepository.updateIsComplete(id, isComplete);
    }

    @Transactional
    public void delete(long taskId) {
        taskRepository.delete(taskId);
    }

    @Transactional
    public void calcProgress(long milestoneId) {
        List<TaskEntity> list = taskRepository.findByMilestoneId(milestoneId);
        int count = list.size();
        int completed = (int) list.stream().filter(e -> e.isComplete() == true).count();
        int progress = (completed * 100) / count;
        milestoneRepository.updateProgress(milestoneId, progress);
    }

    public List<TaskWithLabels> findAllTasksWithLabels() {
        List<TaskEntity> taskEntities = taskRepository.findAll();

        List<TaskWithLabels> tasksWithLabelsList = new ArrayList<>();
        for (TaskEntity taskEntity : taskEntities) {
            taskEntity.setDeadline(deathLevelOperator.adjustDeadline(taskEntity.getDeadline()));

            List<TaskLabelEntity> taskLabelEntities = taskLabelRepository.findByTaskId(taskEntity.getId());

            List<LabelEntity> labels = taskLabelEntities.stream()
                    .map(taskLabelEntity -> labelRepository.findById(taskLabelEntity.getLabelId()))
                    .collect(Collectors.toList());

            TaskWithLabels taskWithLabels = new TaskWithLabels(taskEntity, labels);
            tasksWithLabelsList.add(taskWithLabels);
        }

        return tasksWithLabelsList;
    }

    public TaskWithLabels findTaskWithLabelsByTaskId(Long taskId) {
        TaskEntity taskEntity = taskRepository.findById(taskId);
        List<TaskLabelEntity> taskLabelEntities = taskLabelRepository.findByTaskId(taskId);

        List<LabelEntity> labels = taskLabelEntities.stream()
                .map(taskLabelEntity -> labelRepository.findById(taskLabelEntity.getLabelId()))
                .collect(Collectors.toList());

        TaskWithLabels taskWithLabels = new TaskWithLabels(taskEntity, labels);

        return taskWithLabels;
    }

    public List<TaskWithLabels> findTasksWithLabelsByMilestoneId(Long milestoneId) {
        List<TaskEntity> taskEntities = taskRepository.findByMilestoneId(milestoneId);

        List<TaskWithLabels> tasksWithLabelsList = new ArrayList<>();
        for (TaskEntity taskEntity : taskEntities) {
            taskEntity.setDeadline(deathLevelOperator.adjustDeadline(taskEntity.getDeadline()));

            List<TaskLabelEntity> taskLabelEntities = taskLabelRepository.findByTaskId(taskEntity.getId());

            List<LabelEntity> labels = taskLabelEntities.stream()
                    .map(taskLabelEntity -> labelRepository.findById(taskLabelEntity.getLabelId()))
                    .collect(Collectors.toList());

            TaskWithLabels taskWithLabels = new TaskWithLabels(taskEntity, labels);
            tasksWithLabelsList.add(taskWithLabels);
        }

        return tasksWithLabelsList;
    }

}
