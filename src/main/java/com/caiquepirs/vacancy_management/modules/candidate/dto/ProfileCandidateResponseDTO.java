package com.caiquepirs.vacancy_management.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Data Transfer Object representing a candidate")
public class ProfileCandidateResponseDTO {

    @Schema(description = "Candidate ID", example = "431b0b7e-dab3-4ff1-89b4-911f9110f5e8")
    private UUID id;

    @Schema(description = "Candidate name", example = "Caique Pires")
    private String name;

    @Schema(description = "Candidate username", example = "Caique")
    private String username;

    @Schema(description = "Candidate email", example = "caique@gmail.com")
    private String email;

    @Schema(description = "Candidate description", example = "Junior java backend software engineer")
    private String description;

}
