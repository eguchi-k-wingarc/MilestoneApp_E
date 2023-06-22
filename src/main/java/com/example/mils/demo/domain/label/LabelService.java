package com.example.mils.demo.domain.label;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public void create(String name){
        labelRepository.insert(name);
    }
}
