package com.caiquepirs.vacancy_management.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object representing a company login")
public record AuthCompanyRequestDTO(@Schema(description = "Access username of the company", example = "Amazings")
                                    String username,

                                    @Schema(description = "Password of the company", example = "company1234")
                                    String password) {
}
