package com.caiquepirs.vacancy_management.modules.company.dto;

import java.math.BigDecimal;

public record JobUpdateRequestDTO(String description,
                                  String level,
                                  String benefits,
                                  BigDecimal salary) {
}
