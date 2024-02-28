package com.rf.labrex.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveReportRequest {
    @Size(min = 5)
    private String title;
    @Size(min = 20)
    private String details;
}
