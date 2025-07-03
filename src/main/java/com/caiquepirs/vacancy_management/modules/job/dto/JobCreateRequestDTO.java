package com.caiquepirs.vacancy_management.modules.job.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Data Transfer Object to receive data to create a job vacancy")
public record JobCreateRequestDTO(
                            @Schema(description = "Job vacancy description", example = "Backend Software Developer Vacancy")
                            @NotBlank(message = "Job description is required")
                            String description,

                            @Schema(description = "Job vacancy benefits", example = "Flexibility, meal vouchers, home office assistance")
                            @NotBlank(message = "Job benefits is required")
                            String benefits,

                            @Schema(description = "Job vacancy salary", example = "7987.87")
                            @DecimalMin(value = "0.01", inclusive = true, message = "Salary must be greater than 0")
                            @Digits(integer = 10, fraction = 2, message = "Salary format is invalid")
                            @NotNull(message = "Salary is required")
                            BigDecimal salary,

                            @Schema(description = "Job vacancy level", example = "Junior")
                            @NotBlank(message = "Job level is required")
                            String level) {
}
