package com.caiquepirs.vacancy_management.modules.company.dto;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

public record CompanyUpdateRequestDTO(String name,
                                      String username,

                                      @Email(message = "The field (email) must contain a valid email address.")
                                      String email,

                                      @Length(min = 10, max = 50, message = "Password must contain between 10 and 50 characters.")
                                      String password,

                                      String description,
                                      String website) {
}
