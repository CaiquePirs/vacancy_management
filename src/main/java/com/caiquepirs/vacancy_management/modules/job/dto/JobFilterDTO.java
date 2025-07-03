package com.caiquepirs.vacancy_management.modules.job.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public record JobFilterDTO(

        @Schema(description = "Job vacancy description", example = "Backend Software Developer Vacancy")
        String description,

        @Schema(description = "Job vacancy benefits", example = "Flexibility, meal vouchers, home office assistance")
        String benefits,

        @Schema(description = "Company name", example = "Amazings Ltda")
        String companyName,

        @Schema(description = "Job vacancy level", example = "Junior")
        String level,

        @Schema(description = "Job vacancy maximum salary", example = "1000.88")
        BigDecimal minSalary,

        @Schema(description = "Job vacancy minimum salary", example = "10087.87")
        BigDecimal maxSalary) {
}
