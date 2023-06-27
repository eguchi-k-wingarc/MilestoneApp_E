package com.example.mils.demo.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mils.demo.domain.DeathLevelOperator;
import com.example.mils.demo.domain.DeathLevel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class AdminController {
    private DeathLevelOperator deathLevelOperator;
    
    @GetMapping("/admin-dashboard")
    public String showAdminDashboardForm(Model model) {
        model.addAttribute("allLevels", DeathLevel.values());
        model.addAttribute("currentLevel", deathLevelOperator.getDeathLevel());
        return "admin-dashboard";
    }

    @PostMapping("/set-death-rate")
    public String setdeathLevelOperator(@RequestParam("urgencyLevel") String urgencyLevel) {
        deathLevelOperator.setDeathLevel(DeathLevel.valueOf(urgencyLevel));
        return "redirect:/admin-dashboard";
    }
}
