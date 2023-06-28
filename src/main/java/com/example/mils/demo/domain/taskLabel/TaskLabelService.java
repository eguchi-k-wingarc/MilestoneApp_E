package com.example.mils.demo.domain.taskLabel;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mils.demo.domain.task.TaskEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskLabelService {
    private final TaskLabelRepository taskLabelRepository;

    // public List<TaskLabelEntity> findAll(){
    //     return taskLabelRepository.findAll();
    // }

    // public TaskEntity findByID(Long id){
    //     return taskLabelRepository.findById(id);
    // }

    public List<TaskLabelEntity> findByTaskId(long taskId){
        return taskLabelRepository.findByTaskId(taskId);
    }

    @Transactional
    public void create(long taskId, long labelId){
        taskLabelRepository.create(taskId, labelId);
    }

    @Transactional
    public void delete(long id){
        taskLabelRepository.delete(id); //ボタン付けて消せるようにする
    }
}
