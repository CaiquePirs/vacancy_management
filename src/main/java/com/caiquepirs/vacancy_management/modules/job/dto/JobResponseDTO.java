package com.caiquepirs.vacancy_management.modules.job.dto;

import com.caiquepirs.vacancy_management.modules.job.enums.JobStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Data Transfer Object to return job vacancy data")
public record JobResponseDTO(
                             @Schema(description = "Job vacancy ID", example = "f8240302-9bf9-4835-9a7d-706454f03fe8")
                             UUID id,

                             @Schema(description = "Job vacancy benefits", example = "Flexibility, meal vouchers, home office assistance")
                             String benefits,

                             @Schema(description = "Job vacancy description", example = "Backend Software Developer Vacancy")
                             String description,

                             @Schema(description = "Job vacancy level", example = "Junior")
                             String level,

                             @Schema(description = "Job vacancy salary", example = "7987.87")
                             BigDecimal salary,

                             @Schema(description = "Job vacancy level", example = "ACTIVE")
                             JobStatus status) {
}
