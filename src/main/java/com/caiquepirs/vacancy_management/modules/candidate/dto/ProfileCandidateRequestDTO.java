package com.caiquepirs.vacancy_management.modules.candidate.dto;

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
public class ProfileCandidateRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^\\S+$", message = "The field (username) cannot contain space.")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "The field (email) must contain a valid email address.")
    private String email;

    @NotBlank(message = "Password is required")
    @Length(min = 10, max = 50, message = "Password must contain between 10 and 50 characters")
    private String password;

    private String curriculum;
    private String description;
}
