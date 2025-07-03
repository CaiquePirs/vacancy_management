package com.caiquepirs.vacancy_management.modules.job.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public record JobUpdateRequestDTO(@Schema(description = "Job vacancy description", example = "Backend Software Developer Vacancy")
                                  String description,

                                  @Schema(description = "Job vacancy level", example = "Junior")
                                  String level,

                                  @Schema(description = "Job vacancy benefits", example = "Flexibility, meal vouchers, home office assistance")
                                  String benefits,

                                  @Schema(description = "Job vacancy salary", example = "7987.87")
                                  BigDecimal salary) {
}
