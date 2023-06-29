package com.example.mils.demo.web.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TaskUpdateForm {
    private Long id; // NOTE: インスタンス生成時のみNullを許容するためlong型ではなく、Long型にしている

    private Long milestoneId;

    @NotBlank
    @Size(max = 256)
    private String name;

    @NotBlank
    @Size(max = 256)
    private String description;

    private List<Long> labels;

    @NotBlank
    private String deadlineString;

    private LocalDateTime deadline;

    @AssertTrue(message = "Invalid deadline")
    public boolean isDeadlineValid() {
        if (deadlineString == null || deadlineString.trim().isEmpty()) {
            return true; // 空の場合はバリデーションをスキップ
        }
        try {
            LocalDateTime parsedDeadline = LocalDateTime.parse(deadlineString, DateTimeFormatter.ISO_DATE_TIME);
            setDeadline(parsedDeadline);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
