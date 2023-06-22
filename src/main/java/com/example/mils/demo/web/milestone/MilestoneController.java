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

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/milestones")
public class MilestoneController {

    private final MilestoneService milestoneService;

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
     * @param id    マイルストーンのID
     * @param model 画面に渡すデータを格納するModelオブジェクト
     * @return 詳細画面のテンプレート名
     */
    @GetMapping("/{id}")
    public String showDetail(@PathVariable("id") long id, Model model) {
        MilestoneEntity milestone = milestoneService.findById(id);
        model.addAttribute("milestone", milestone);

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
        milestoneService.create(creationForm.getname(), creationForm.getDescription());
        return "redirect:/milestones";
    }
}