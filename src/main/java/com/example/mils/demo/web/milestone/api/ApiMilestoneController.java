package com.example.mils.demo.web.milestone.api;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mils.demo.domain.milestone.MilestoneProgressDto;
import com.example.mils.demo.domain.milestone.MilestoneService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/milestones")
public class ApiMilestoneController {
    private final MilestoneService milestoneService;

    /**
     * 一覧の消化率取得API
     *
     * @return List<MilestoneProgressDto>
     */
    @GetMapping()
    public List<MilestoneProgressDto> index() {
        List<MilestoneProgressDto> milestoneProgressDtoList = milestoneService.getAllProgressDto();

        return milestoneProgressDtoList;
    }

    /**
     * 詳細の消化率取得API
     *
     * @param model 画面に渡すデータを格納するModelオブジェクト
     * @return MilestoneProgressDto 
     */
    @GetMapping("{id}")
    public MilestoneProgressDto show(@PathVariable Long id) {
        MilestoneProgressDto milestoneProgressDto = milestoneService.getProgressDto(id);

        return milestoneProgressDto;
    }
}
