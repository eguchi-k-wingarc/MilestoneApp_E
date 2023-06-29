package com.example.mils.demo.web.milestone;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MilestoneUpdateForm {
    private Long id; // NOTE: インスタンス生成時のみNullを許容するためlong型ではなく、Long型にしている

    @NotBlank
    @Size(max = 256)
    private String name;

    @NotBlank
    @Size(max = 256)
    private String description;

    @NotBlank
    private String deadlineString;

    private LocalDateTime deadline;

    @AssertTrue(message = "Invalid deadline")
    public boolean isDeadlineValid() {
        if (deadlineString == null || deadlineString.trim().isEmpty()) {
            return true;
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
