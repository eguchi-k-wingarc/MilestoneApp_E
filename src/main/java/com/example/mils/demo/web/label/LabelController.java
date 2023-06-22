package com.example.mils.demo.web.label;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mils.demo.domain.label.LabelEntity;
import com.example.mils.demo.domain.label.LabelService;
import com.example.mils.demo.domain.task.TaskService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/milestones")
public class LabelController {
    
    private final TaskService taskService;
    private final LabelService labelService;

    // @GetMapping()
    // public String showList(Model model) {
    //     List<LabelEntity> labelList = labelService.findAll();

    //     return ""
    // }
}
