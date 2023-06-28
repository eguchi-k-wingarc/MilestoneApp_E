package com.example.mils.demo.web.label;

import java.util.List;

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
    public String showList(Model model, @AuthenticationPrincipal UserDetails loginUser) {
        List<LabelEntity> labelList = labelService.findAll();

        model.addAttribute("labelList", labelList);
        model.addAttribute("loginUser", loginUser);
        return "labels/list";
    }

    /**
     * ラベルの作成フォームを表示します。
     *
     * @param creationForm ラベル作成フォームオブジェクト
     * @return 作成フォームのテンプレート名
     */
    @GetMapping("/create")
    public String showCreationForm(@ModelAttribute LabelCreateForm creationForm, Model model,
            @AuthenticationPrincipal UserDetails loginUser) {
        model.addAttribute("loginUser", loginUser);
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
    public String create(@Validated LabelCreateForm creationForm, BindingResult bindingResult, Model model,
            @AuthenticationPrincipal UserDetails loginUser) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(creationForm, model, loginUser);
        }
        labelService.create(creationForm.getName(), creationForm.getColor());
        return "redirect:/labels";
    }

    /**
     * ラベルの編集フォームを表示します。
     *
     * @param labelId       編集するラベルのID
     * @param form ラベル編集フォームオブジェクト
     * @return 編集フォームのテンプレート名
     */
    @GetMapping("/{labelId}/update")
    public String showEditForm(@PathVariable("labelId") Long labelId, LabelUpdateForm form, Model model,
            @AuthenticationPrincipal UserDetails loginUser) {
        LabelEntity label = labelService.findById(labelId);

        if (label != null) {
            form.setId(label.getId());
            form.setName(label.getName());
            form.setColor(label.getColor());
        } else {
            return "redirect:/labels";
        }

        model.addAttribute("loginUser", loginUser);
        model.addAttribute("label", label);
        return "labels/updateForm";
    }

    /**
     * ラベルを更新します。
     *
     * @param labelId            更新するラベルのID
     * @param form      ラベル編集フォームオブジェクト
     * @param bindingResult バリデーション結果を保持するBindingResultオブジェクト
     * @return 一覧画面にリダイレクトするURL
     */
    @PostMapping("/{labelId}/update")
    public String update(@PathVariable("labelId") Long labelId, @Validated LabelUpdateForm form, BindingResult bindingResult,
            Model model, @AuthenticationPrincipal UserDetails loginUser) {
        if (bindingResult.hasErrors()) {
            return showEditForm(labelId, form, model, loginUser);
        }
        labelService.update(labelId, form.getName(), form.getColor());
        return "redirect:/labels";
    }

    /**
     * ラベルを削除します。
     *
     * @param labelId 削除するラベルのID
     * @return 一覧画面にリダイレクトするURL
     */
    @PostMapping("/{labelId}/delete")
    public String delete(@PathVariable("labelId") Long labelId) {
        labelService.delete(labelId);
        return "redirect:/labels";
    }

}
