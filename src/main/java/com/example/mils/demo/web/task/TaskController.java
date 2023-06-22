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
            Model model) {
        TaskEntity task = taskService.findById(taskId);
        model.addAttribute("task", task);

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
            @ModelAttribute TaskCreateForm creationForm, Model model) {
        model.addAttribute("milestoneId", milestoneId);
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
    @PostMapping
    public String create(@PathVariable("milestoneId") Long milestoneId, @Validated TaskCreateForm creationForm,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(milestoneId, creationForm, model);
        }
        taskService.create(milestoneId, creationForm.getName(), creationForm.getDescription(), creationForm.getDeadline());
        return "redirect:/milestones/" + milestoneId + "/tasks";
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
            TaskUpdateForm form, Model model) {
        TaskEntity task = taskService.findById(taskId);
        if (task != null) {
            form.setId(task.getId());
            form.setName(task.getName());
            form.setDescription(task.getDescription());
        } else {
            return "redirect:/milestones/" + milestoneId + "/tasks";
        }
        model.addAttribute("taskForm", form);
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
            @Validated TaskUpdateForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showEditForm(milestoneId, taskId, form, model);
        }

        taskService.update(taskId, form.getName(), form.getDescription(), form.getDeadline());
        return "redirect:/milestones/" + milestoneId + "/tasks";
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
        return "redirect:/milestones/" + milestoneId + "/tasks";
    }
}
