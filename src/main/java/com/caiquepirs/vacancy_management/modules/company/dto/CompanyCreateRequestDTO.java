package com.caiquepirs.vacancy_management.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

@Schema(description = "Data Transfer Object representing a company")
public record CompanyCreateRequestDTO(
                                @Schema(description = "Name of the company", example = "Tech Amazings Ltda")
                                @NotBlank(message = "Name is required.")
                                String name,

                                @Schema(description = "Access username of the company", example = "Amazings")
                                @NotBlank(message = "Username is required")
                                @Pattern(regexp = "^\\S+$", message = "The field (username) cannot contain space.")
                                String username,

                                @Schema(description = "Email of the company", example = "tech@amazings.com.br")
                                @Email(message = "The field (email) must contain a valid email address.")
                                @NotBlank(message = "Email is required.")
                                String email,

                                @Schema(description = "Username of the company", example = "company1234")
                                @Length(min = 10, max = 50, message = "Password must contain between 10 and 50 characters.")
                                @NotBlank(message = "Password is required.")
                                String password,

                                @Schema(description = "Website of the company", example = "amazings.com.br")
                                @NotBlank(message = "Website is required.")
                                String website,

                                @Schema(description = "Description of the company", example = "Company specialized in sales of electronic products")
                                @NotBlank(message = "Description is required.")
                                String description) {
}
