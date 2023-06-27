package com.example.mils.demo.domain.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.mils.demo.domain.DeathLevelOperator;
import com.example.mils.demo.domain.milestone.MilestoneRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
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
}
