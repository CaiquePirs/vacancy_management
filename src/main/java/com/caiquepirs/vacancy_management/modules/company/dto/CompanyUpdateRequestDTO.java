package com.caiquepirs.vacancy_management.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Schema(description = "Data Transfer Object representing a company")
public record CompanyUpdateRequestDTO(@Schema(description = "Name of the company", example = "Tech Amazings")
                                      String name,

                                      @Schema(description = "Access username of the company", example = "Amazings")
                                      String username,

                                      @Schema(description = "Email of the company", example = "tech@amazings.com.br")
                                      @Email(message = "The field (email) must contain a valid email address.")
                                      String email,

                                      @Schema(description = "Username of the company", example = "company1234")
                                      @Length(min = 10, max = 50, message = "Password must contain between 10 and 50 characters.")
                                      String password,

                                      @Schema(description = "Description of the company", example = "Company specialized in sales of electronic products")
                                      String description,

                                      @Schema(description = "Website of the company", example = "amazings.com.br")
                                      String website) {
}
