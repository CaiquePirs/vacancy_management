package com.caiquepirs.vacancy_management.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data Transfer Object representing a candidate login")
public class AuthCandidateResponseDTO {

    @Schema(description = "Access token JWT", example = "bearer<token>")
    private String access_token;

    @Schema(description = "Token expiration time", example = "1751561916465")
    private Long expire_in;
}
