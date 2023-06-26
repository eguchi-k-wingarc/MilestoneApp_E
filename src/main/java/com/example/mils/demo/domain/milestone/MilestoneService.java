package com.example.mils.demo.domain.milestone;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MilestoneService {
    private final MilestoneRepository milestoneRepository;

    public List<MilestoneEntity> findAll() {
        List<MilestoneEntity> list = milestoneRepository.findAll();
        List<MilestoneEntity> processedList = list.stream().map(milestone ->{
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime deadline = milestone.getDeadline();
            Duration duration = Duration.between(today, deadline).dividedBy((long)10);
            milestone.setDeadline(today.plus(duration));
            return milestone;
        }).collect(Collectors.toList());
        return processedList;
    }

    public MilestoneEntity findById(long id) {
        MilestoneEntity milestone = milestoneRepository.findById(id);
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime deadline = milestone.getDeadline();
        Duration duration = Duration.between(today, deadline).dividedBy((long)10);
        milestone.setDeadline(today.plus(duration));
        return milestone;
    }

    @Transactional
    public void create(String name, String description, LocalDateTime deadline) {
        milestoneRepository.create(name, description, deadline);
    }

    @Transactional
    public void update(long id, String name, String description, LocalDateTime deadline) {
        milestoneRepository.update(id, name, description, deadline);
    }

    @Transactional
    public void delete(long milestoneId) {
        milestoneRepository.delete(milestoneId);
    }
}
