package com.caiquepirs.vacancy_management.modules.company.dto;

import com.caiquepirs.vacancy_management.modules.company.enuns.JobStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record JobResponseDTO(UUID id,
                             String benefits,
                             String description,
                             String level,
                             BigDecimal salary,
                             JobStatus status) {
}
