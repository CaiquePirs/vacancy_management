package com.caiquepirs.vacancy_management.modules.job.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record JobCreateRequestDTO(@NotBlank(message = "Job description is required")
                            String description,

                                  @NotBlank(message = "Job benefits is required")
                            String benefits,

                                  @NotNull(message = "Job salary is required")
                            BigDecimal salary,

                                  @NotBlank(message = "Job level is required")
                            String level) {
}
