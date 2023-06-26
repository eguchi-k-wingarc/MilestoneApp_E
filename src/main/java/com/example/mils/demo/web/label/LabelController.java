package com.example.mils.demo.web.label;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mils.demo.domain.label.LabelEntity;
import com.example.mils.demo.domain.label.LabelService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/labels")
public class LabelController {
    private final LabelService labelService;

    /**
     * ラベル一覧画面を表示
     *
     * @param model 画面に渡すデータを格納するModelオブジェクト
     * @return 一覧画面のテンプレート名
     */
    @GetMapping()
    public String showList(Model model) {
        List<LabelEntity> labelList = labelService.findAll();

        model.addAttribute("labelList", labelList);

        return "labels/list";
    }

    /**
     * ラベルの作成フォームを表示します。
     *
     * @param creationForm ラベル作成フォームオブジェクト
     * @return 作成フォームのテンプレート名
     */
    @GetMapping("/create")
    public String showCreationForm(@ModelAttribute LabelCreateForm creationForm) {
        return "labels/creationForm";
    }

    /**
     * ラベルを作成します。
     *
     * @param creationForm  ラベル作成フォームオブジェクト
     * @param bindingResult バリデーション結果を保持するBindingResultオブジェクト
     * @return 一覧画面にリダイレクトするURL
     */
    @PostMapping
    public String create(@Validated LabelCreateForm creationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(creationForm);
        }
        labelService.create(creationForm.getName(), creationForm.getColor());
        return "redirect:/labels";
    }

}
