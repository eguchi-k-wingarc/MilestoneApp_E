package com.example.mils.demo.web.task;

import com.example.mils.demo.domain.label.LabelEntity;
import com.example.mils.demo.domain.label.LabelService;
import com.example.mils.demo.domain.milestone.*;
import java.lang.Boolean;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.mils.demo.domain.task.TaskService;
import com.example.mils.demo.domain.task.TaskWithLabels;
import com.example.mils.demo.domain.taskLabel.TaskLabelService;
import com.example.mils.demo.domain.user.UserEntity;
import com.example.mils.demo.domain.user.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/milestones/{milestoneId}/tasks")
public class TaskController {

    private final TaskService taskService;
    private final MilestoneService milestoneService;
    private final UserService userService;
    private final LabelService labelService;
    private final TaskLabelService taskLabelService;

    /**
     * 指定されたマイルストーンIDとタスクIDに対するタスク詳細画面を表示します。
     *
     * @param milestoneId マイルストーンのID
     * @param taskId      タスクのID
     * @param model       画面に渡すデータを格納するModelオブジェクト
     * @return 詳細画面のテンプレート名
     */
    @GetMapping("/{taskId}")
    public String showDetail(@PathVariable("milestoneId") Long milestoneId, @PathVariable("taskId") Long taskId,
            Model model, @AuthenticationPrincipal UserDetails loginUser) {
        TaskWithLabels taskWithLabels = taskService.findTaskWithLabelsByTaskId(taskId);
        MilestoneEntity milestone = milestoneService.findById((long) taskWithLabels.getTask().getMilestoneId());
        UserEntity user = userService.findById(milestone.getUserId());
        UserEntity taskUser = userService.findById(taskWithLabels.getTask().getUserId());
        model.addAttribute("user", user);
        model.addAttribute("taskuser", taskUser);
        model.addAttribute("milestone", milestone);
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("taskWithLabels", taskWithLabels);
        return "tasks/detail";
    }

    /**
     * タスクの作成フォームを表示します。
     *
     * @param milestoneId  マイルストーンのID
     * @param creationForm タスク作成フォームオブジェクト
     * @return 作成フォームのテンプレート名
     */
    @GetMapping("/create")
    public String showCreationForm(@PathVariable("milestoneId") Long milestoneId,
            @ModelAttribute TaskCreateForm creationForm, Model model, @AuthenticationPrincipal UserDetails loginUser) {
        model.addAttribute("milestoneId", milestoneId);
        model.addAttribute("loginUser", loginUser);
        return "tasks/creationForm";
    }

    /**
     * 新たなタスクを作成します。
     *
     * @param milestoneId   マイルストーンのID
     * @param creationForm  タスク作成フォームオブジェクト
     * @param bindingResult バリデーション結果を保持するBindingResultオブジェクト
     * @return タスク一覧画面にリダイレクトするURL
     */
    @PostMapping("/create")
    public String create(@PathVariable("milestoneId") Long milestoneId, @Validated TaskCreateForm creationForm,
            BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails loginUser) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(milestoneId, creationForm, model, loginUser);
        }
        taskService.create(milestoneId, creationForm.getName(), creationForm.getDescription(),
                creationForm.getDeadline());
        taskService.calcProgress(milestoneId);
        return "redirect:/milestones/" + milestoneId;
    }

    /**
     * タスクの編集フォームを表示します。
     *
     * @param milestoneId マイルストーンのID
     * @param taskId      タスクのID
     * @param model       画面に渡すデータを格納するModelオブジェクト
     * @return 編集フォームのテンプレート名
     */
    @GetMapping("/{taskId}/update")
    public String showEditForm(@PathVariable("milestoneId") Long milestoneId, @PathVariable("taskId") Long taskId,
            TaskUpdateForm form, Model model, @AuthenticationPrincipal UserDetails loginUser) {
        TaskWithLabels taskWithLabels = taskService.findTaskWithLabelsByTaskId(taskId);
        if (taskWithLabels.getTask() != null) {
            form.setId(taskWithLabels.getTask().getId());
            form.setName(taskWithLabels.getTask().getName());
            form.setDescription(taskWithLabels.getTask().getDescription());
            form.setLabels(taskWithLabels.getLabels().stream().map(LabelEntity::getId).collect(Collectors.toList()));
        } else {
            return "redirect:/milestones/" + milestoneId;
        }
        List<LabelEntity> allLabels = labelService.findAll();
        model.addAttribute("allLabels", allLabels);
        model.addAttribute("taskUpdateForm", form);
        model.addAttribute("loginUser", loginUser);
        return "tasks/updateForm";
    }

    /**
     * タスクを更新します。
     *
     * @param milestoneId   マイルストーンのID
     * @param taskId        タスクのID
     * @param form          タスク編集フォームオブジェクト
     * @param bindingResult バリデーション結果を保持するBindingResultオブジェクト
     * @return タスク一覧画面にリダイレクトするURL
     */
    @PostMapping("/{taskId}/update")
    public String update(@PathVariable("milestoneId") Long milestoneId, @PathVariable("taskId") Long taskId,
            @Validated TaskUpdateForm form, BindingResult bindingResult, Model model,
            @AuthenticationPrincipal UserDetails loginUser) {
        if (bindingResult.hasErrors()) {
            return showEditForm(milestoneId, taskId, form, model, loginUser);
        }

        taskService.update(taskId, form.getName(), form.getDescription(), form.getDeadline());
        taskLabelService.replaceTaskWithLabels(taskId, form.getLabels());
        taskService.calcProgress(milestoneId);
        return "redirect:/milestones/" + milestoneId;
    }

    /**
     * タスクのisCompleteを更新します。
     *
     * @param milestoneId   マイルストーンのID
     * @param taskId        タスクのID
     * @param form          タスク編集フォームオブジェクト
     * @param bindingResult バリデーション結果を保持するBindingResultオブジェクト
     * @return タスク一覧画面にリダイレクトするURL
     */

    @PostMapping("/{taskId}/update-isComplete")
    public String updateIsComplete(@PathVariable("milestoneId") Long milestoneId, @PathVariable("taskId") Long taskId,
            TaskIsCompleteUpdateForm form, Model model) {
        Boolean isComplete = form.getIsComplete();
        taskService.updateIsComplete(taskId, isComplete);
        taskService.calcProgress(milestoneId);
        return "redirect:/milestones/" + milestoneId;
    }

    /**
     * タスクを削除します。
     *
     * @param milestoneId マイルストーンのID
     * @param taskId      タスクのID
     * @return タスク一覧画面にリダイレクトするURL
     */
    @PostMapping("/{taskId}/delete")
    public String delete(@PathVariable("milestoneId") Long milestoneId, @PathVariable("taskId") Long taskId) {
        taskService.delete(taskId);
        taskService.calcProgress(milestoneId);
        return "redirect:/milestones/" + milestoneId;
    }
}
