package com.example.mils.demo.domain.task;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mils.demo.domain.milestone.MilestoneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final MilestoneRepository milestoneRepository;

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
        int completed = (int)list.stream().filter(e -> e.isComplete()==true).count();
        int progress = (completed * 100) / count;
        milestoneRepository.updateProgress(milestoneId, progress);
        
    }
}
