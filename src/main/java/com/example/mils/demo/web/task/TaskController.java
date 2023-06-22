package com.example.mils.demo.web.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mils.demo.domain.task.TaskEntity;
import com.example.mils.demo.domain.task.TaskService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/milestones/{milestoneId}/tasks")
public class TaskController {
    
    private final TaskService taskService;

    @GetMapping("/{taskId}")
    public String showDetail(@PathVariable("milestoneId") Long milestoneId, @PathVariable("taskId") Long taskId, Model model) {
        TaskEntity task = taskService.findById(taskId);
        model.addAttribute("task", task);

        return "tasks/detail";
    }

    @GetMapping("/create")
    public String showCreationForm(@PathVariable("milestoneId") Long milestoneId, @ModelAttribute TaskForm creationForm) {
        return "tasks/creationForm";
    }

    @PostMapping
    public String create(@PathVariable("milestoneId") Long milestoneId, @Validated TaskForm creationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(milestoneId, creationForm);
        }
        taskService.create(creationForm.getName(), creationForm.getDescription());
        return "redirect:/milestones/" + milestoneId + "/tasks";
    }

    @PutMapping("/{taskId}")
    public String update(@PathVariable("milestoneId") Long milestoneId, @PathVariable("id") Long taskId) {
        return "tasks/update";
    }
}
