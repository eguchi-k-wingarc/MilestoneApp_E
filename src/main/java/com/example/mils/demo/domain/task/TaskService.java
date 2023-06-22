package com.example.mils.demo.domain.task;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<TaskEntity> findAll() {
        return taskRepository.findAll();
    }

    public TaskEntity findById(long id) {
        return taskRepository.findById(id);
    }

    public List<TaskEntity> findByMilestoneId(Long milestoneId) { // TODO: nullを許可しないlong型に変更する
        return taskRepository.findByMilestoneId(milestoneId);
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
    public void delete(long taskId) {
        taskRepository.delete(taskId);
    }
}
