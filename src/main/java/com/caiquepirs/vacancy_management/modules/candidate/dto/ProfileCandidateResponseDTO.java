package com.caiquepirs.vacancy_management.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfileCandidateResponseDTO {

    private UUID id;
    private String name;
    private String username;
    private String email;
    private String description;

}
