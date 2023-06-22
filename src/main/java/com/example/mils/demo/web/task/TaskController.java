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
@RequestMapping("/tasks")
public class TaskController {
    
    private final TaskService taskService;

    @GetMapping("/{id}")
    public String showDetail(@PathVariable("id") long id, Model model) {
        TaskEntity task = taskService.findById(id);
        model.addAttribute("task", task);

        return "tasks/detail";
    }

    @GetMapping("/create")
    public String showCreationForm(@ModelAttribute TaskForm creationForm) {
        return "tasks/creationForm";
    }

    @PostMapping
    public String create(@Validated TaskForm creationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(creationForm);
        }
        taskService.create(creationForm.getName(), creationForm.getDescription());
        return "redirect:/tasks";
    }

    @PutMapping
    public String update() {
        return "tasks/update";
    }
}
