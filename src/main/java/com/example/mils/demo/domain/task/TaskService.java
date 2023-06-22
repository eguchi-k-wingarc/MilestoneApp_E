package com.example.mils.demo.domain.task;

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
    public void create(String name, String description) {
        taskRepository.create(name, description);
    }
}
