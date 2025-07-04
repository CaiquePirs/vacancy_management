package com.caiquepirs.vacancy_management.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object representing a candidate login")
public record AuthCandidateRequestDTO(
                    @Schema(description = "Access username of the candidate", example = "Caique")
                    String username,

                    @Schema(description = "Password of the company", example = "caique123")
                    String password) {
}
