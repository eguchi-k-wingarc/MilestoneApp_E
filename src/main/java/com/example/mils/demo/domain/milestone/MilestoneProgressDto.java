package com.example.mils.demo.domain.milestone;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * マイルストーンの消化率を取得するためのDTOクラス
 */
@AllArgsConstructor
@Data
public class MilestoneProgressDto {
    private Long id;
    private Long userId;
    private Integer progress;
}
