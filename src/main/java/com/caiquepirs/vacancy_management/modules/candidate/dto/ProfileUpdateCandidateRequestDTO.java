package com.caiquepirs.vacancy_management.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

@Schema(description = "Data Transfer Object representing a candidate")
public record ProfileUpdateCandidateRequestDTO(@Schema(description = "Candidate name", example = "Caique Pires")
                                               String name,

                                               @Schema(description = "Candidate username", example = "Caique")
                                               @Pattern(regexp = "^\\S+$", message = "The field (username) cannot contain space.")
                                               String username,

                                               @Schema(description = "Candidate email", example = "caique@gmail.com")
                                               @Email(message = "The field (email) must contain a valid email address.")
                                               String email,

                                               @Schema(description = "Candidate password", example = "caique123")
                                               @Length(min = 10, max = 50, message = "Password must contain between 10 and 50 characters")
                                               String password,

                                               @Schema(description = "Candidate description", example = "Junior java backend software engineer")
                                               String description,

                                               @Schema(description = "Candidate curriculum", example = "Upload")
                                               String curriculum) {
}
