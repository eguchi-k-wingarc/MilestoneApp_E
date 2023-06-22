package com.example.mils.demo.web.milestone;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mils.demo.domain.milestone.MilestoneEntity;
import com.example.mils.demo.domain.milestone.MilestoneService;
import com.example.mils.demo.domain.task.TaskEntity;
import com.example.mils.demo.domain.task.TaskService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/milestones")
public class MilestoneController {

    private final MilestoneService milestoneService;
    private final TaskService taskService;

    /**
     * 一覧画面を表示します。
     *
     * @param model 画面に渡すデータを格納するModelオブジェクト
     * @return 一覧画面のテンプレート名
     */
    @GetMapping()
    public String showList(Model model) {
        List<MilestoneEntity> milestoneList = milestoneService.findAll();

        model.addAttribute("milestoneList", milestoneList);

        return "milestones/list";
    }

    /**
     * 指定されたIDのマイルストーンの詳細画面を表示します。
     *
     * @param milestoneId マイルストーンのID
     * @param model       画面に渡すデータを格納するModelオブジェクト
     * @return 詳細画面のテンプレート名
     */
    @GetMapping("/{milestoneId}")
    public String showDetail(@PathVariable("milestoneId") Long milestoneId, Model model) { // TODO: nullを許可しないlong型に変更する
        MilestoneEntity milestone = milestoneService.findById(milestoneId);
        List<TaskEntity> task = taskService.findByMilestoneId(milestoneId);
        model.addAttribute("milestone", milestone);
        model.addAttribute("task", task);

        return "milestones/detail";
    }

    /**
     * マイルストーンの作成フォームを表示します。
     *
     * @param creationForm マイルストーン作成フォームオブジェクト
     * @return 作成フォームのテンプレート名
     */
    @GetMapping("/create")
    public String showCreationForm(@ModelAttribute MilestoneForm creationForm) {
        return "milestones/creationForm";
    }

    /**
     * マイルストーンを作成します。
     *
     * @param creationForm  マイルストーン作成フォームオブジェクト
     * @param bindingResult バリデーション結果を保持するBindingResultオブジェクト
     * @return 一覧画面にリダイレクトするURL
     */
    @PostMapping
    public String create(@Validated MilestoneForm creationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(creationForm);
        }
        milestoneService.create(creationForm.getName(), creationForm.getDescription(), creationForm.getDeadline());
        return "redirect:/milestones";
    }

    /**
     * マイルストーンの編集フォームを表示します。
     *
     * @param milestoneId マイルストーンのID
     * @param model       画面に渡すデータを格納するModelオブジェクト
     * @return 編集フォームのテンプレート名
     */
    @GetMapping("/{milestoneId}/edit")
    public String showEditForm(@PathVariable("milestoneId") Long milestoneId, MilestoneForm form, Model model) {
        MilestoneEntity milestone = milestoneService.findById(milestoneId);
        form.setName(milestone.getName());
        form.setDescription(milestone.getDescription());
        form.setDeadline(milestone.getDeadline());
        model.addAttribute("milestoneForm", form);
        return "milestones/editForm";
    }

    /**
     * マイルストーンを更新します。
     *
     * @param milestoneId   マイルストーンのID
     * @param form          マイルストーン編集フォームオブジェクト
     * @param bindingResult バリデーション結果を保持するBindingResultオブジェクト
     * @return 一覧画面にリダイレクトするURL
     */
    @PostMapping("/{milestoneId}/update")
    public String update(@PathVariable("milestoneId") Long milestoneId, @Validated MilestoneForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showEditForm(milestoneId, form, model);
        }
        
        milestoneService.update(milestoneId, form.getName(), form.getDescription(), form.getDeadline());
        return "redirect:/milestones";
    }

    /**
     * マイルストーンを削除します。
     *
     * @param milestoneId マイルストーンのID
     * @return 一覧画面にリダイレクトするURL
     */
    @PostMapping("/{milestoneId}/delete")
    public String delete(@PathVariable("milestoneId") Long milestoneId) {
        milestoneService.delete(milestoneId);
        return "redirect:/milestones";
    }
}