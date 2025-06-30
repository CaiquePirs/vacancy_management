package com.caiquepirs.vacancy_management.modules.company.dto;

import java.util.UUID;

public record CompanyResponseDTO(UUID id,
                                 String name,
                                 String username,
                                 String email,
                                 String website,
                                 String description) {
}
