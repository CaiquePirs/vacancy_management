package com.caiquepirs.vacancy_management.modules.candidate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record ProfileUpdateCandidateRequestDTO(String name,
                                               @Pattern(regexp = "^\\S+$", message = "The field (username) cannot contain space.")
                                               String username,

                                               @Email(message = "The field (email) must contain a valid email address.")
                                               String email,

                                               @Length(min = 10, max = 50, message = "Password must contain between 10 and 50 characters")
                                               String password,
                                               String description,
                                               String curriculum) {
}
