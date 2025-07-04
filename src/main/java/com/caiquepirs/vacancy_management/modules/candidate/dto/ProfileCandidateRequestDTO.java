package com.caiquepirs.vacancy_management.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Data Transfer Object representing a candidate")
public class ProfileCandidateRequestDTO {

    @Schema(description = "Candidate name", example = "Caique Pires")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Candidate username", example = "Caique")
    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^\\S+$", message = "The field (username) cannot contain space.")
    private String username;

    @Schema(description = "Candidate email", example = "caique@gmail.con")
    @NotBlank(message = "Email is required")
    @Email(message = "The field (email) must contain a valid email address.")
    private String email;

    @Schema(description = "Candidate password", example = "caique123")
    @NotBlank(message = "Password is required")
    @Length(min = 10, max = 50, message = "Password must contain between 10 and 50 characters")
    private String password;

    @Schema(description = "Candidate curriculum", example = "Upload")
    @NotBlank(message = "Curriculum is required")
    private String curriculum;

    @Schema(description = "Candidate description", example = "Junior java backend software engineer")
    @NotBlank(message = "Description is required")
    private String description;
}
