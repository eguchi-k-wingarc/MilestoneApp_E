package com.example.mils.demo.domain.label;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabelService {
    private final LabelRepository labelRepository;

    public List<LabelEntity> findAll() {
        return labelRepository.findAll();
    }

    public LabelEntity findById(long id) {
        return labelRepository.findById(id);
    }

    @Transactional
    public void create(String name, String color) {
        labelRepository.create(name, color);
    }

    @Transactional
    public void update(long id, String name, String color) {
        labelRepository.update(id, name, color);
    }

    @Transactional
    public void delete(Long labelId) {
        labelRepository.delete(labelId);
    }
}
