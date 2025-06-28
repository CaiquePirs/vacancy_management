package com.caiquepirs.vacancy_management.modules.candidate.useCases;

import com.caiquepirs.vacancy_management.modules.candidate.CandidateRepository;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProfileCandidateUseCase {

    private final CandidateRepository repository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate){
        var candidate = repository.findById(idCandidate)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

       return ProfileCandidateResponseDTO.builder()
                .id(candidate.getId())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .build();
    }

}
