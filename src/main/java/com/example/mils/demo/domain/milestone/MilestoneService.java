package com.example.mils.demo.domain.milestone;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.mils.demo.domain.DeathLevelOperator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MilestoneService {
    private final MilestoneRepository milestoneRepository;
    private final DeathLevelOperator deathLevelOperator;

    public List<MilestoneEntity> findAll() {
        List<MilestoneEntity> list = milestoneRepository.findAll();
        List<MilestoneEntity> processedList = list.stream().map(milestone -> {
            milestone.setDeadline(deathLevelOperator.adjustDeadline(milestone.getDeadline()));
            return milestone;
        }).collect(Collectors.toList());
        return processedList;
    }

    public MilestoneEntity findById(long id) {
        MilestoneEntity milestone = milestoneRepository.findById(id);
        milestone.setDeadline(deathLevelOperator.adjustDeadline(milestone.getDeadline()));
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

    public List<MilestoneProgressDto> getAllProgressDto() {
        List<MilestoneEntity> milestones = milestoneRepository.findAll();

        return milestones.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public MilestoneProgressDto getProgressDto(Long id) {
        MilestoneEntity milestone = milestoneRepository.findById(id);

        return convertToDto(milestone);
    }

    private MilestoneProgressDto convertToDto(MilestoneEntity entity) {
        return new MilestoneProgressDto(
                entity.getId(),
                entity.getUserId(),
                entity.getProgress());
    }
}
