package com.caiquepirs.vacancy_management.modules.job.dto;

import java.math.BigDecimal;

public record JobFilterDTO(
        String description,
        String benefits,
        String companyName,
        String level,
        BigDecimal minSalary,
        BigDecimal maxSalary) {
}
