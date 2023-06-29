package com.example.mils.demo.web.task;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import com.example.mils.demo.domain.task.TaskService;
import com.example.mils.demo.domain.task.TaskWithLabels;
import lombok.AllArgsConstructor;

/**
 * TaskListController
 */
@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskListController {

    private final TaskService taskService;

    /**
     * 全てのタスク一覧画面を表示します。
     *
     * @param model 画面に渡すデータを格納するModelオブジェクト
     * @return 詳細画面のテンプレート名
     */
    @GetMapping
    public String showList(Model model, @AuthenticationPrincipal UserDetails loginUser) {
        List<TaskWithLabels> taskWithLabelsList = taskService.findAllTasksWithLabels();

        model.addAttribute("taskWithLabelsList", taskWithLabelsList);
        model.addAttribute("loginUser", loginUser);
        return "tasks/list";
    }
}