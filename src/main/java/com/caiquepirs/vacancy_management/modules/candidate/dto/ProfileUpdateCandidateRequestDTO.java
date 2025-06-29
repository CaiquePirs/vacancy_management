package com.caiquepirs.vacancy_management.modules.candidate.dto;

public record ProfileUpdateCandidateRequestDTO(String name,
                                               String username,
                                               String email,
                                               String password,
                                               String description,
                                               String curriculum) {
}
