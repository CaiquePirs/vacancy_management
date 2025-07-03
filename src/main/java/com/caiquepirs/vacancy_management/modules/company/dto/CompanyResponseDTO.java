package com.caiquepirs.vacancy_management.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Data Transfer Object representing a company")
public record CompanyResponseDTO(@Schema(description = "Unique identifier of the company", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                 UUID id,

                                 @Schema(description = "Name of the company", example = "Tech Amazings Ltda")
                                 String name,

                                 @Schema(description = "Access username of the company", example = "Amazings")
                                 String username,

                                 @Schema(description = "Email of the company", example = "tech@amazings.com.br")
                                 String email,

                                 @Schema(description = "Website of the company", example = "amazings.com.br")
                                 String website,

                                 @Schema(description = "Description of the company", example = "Company specialized in sales of electronic products")
                                 String description) {
}
