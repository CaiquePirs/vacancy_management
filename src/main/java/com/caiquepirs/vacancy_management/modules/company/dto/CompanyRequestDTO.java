package com.caiquepirs.vacancy_management.modules.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CompanyRequestDTO(@NotBlank(message = "Name is required.")
                                String name,

                                @NotBlank(message = "Username is required")
                                @Pattern(regexp = "^\\S+$", message = "The field (username) cannot contain space.")
                                String username,

                                @Email(message = "The field (email) must contain a valid email address.")
                                @NotBlank(message = "Email is required.")
                                String email,

                                @Length(min = 10, max = 50, message = "Password must contain between 10 and 50 characters.")
                                @NotBlank(message = "Password is required.")
                                String password,

                                @NotBlank(message = "Website is required.")
                                String website,

                                @NotBlank(message = "Description is required.")
                                String description) {
}
