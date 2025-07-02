package com.caiquepirs.vacancy_management.modules.job.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record JobCreateRequestDTO(@NotBlank(message = "Job description is required")
                            String description,

                            @NotBlank(message = "Job benefits is required")
                            String benefits,

                            @DecimalMin(value = "0.01", inclusive = true, message = "Salary must be greater than 0")
                            @Digits(integer = 10, fraction = 2, message = "Salary format is invalid")
                            @NotNull(message = "Salary is required")
                            BigDecimal salary,

                            @NotBlank(message = "Job level is required")
                            String level) {
}
