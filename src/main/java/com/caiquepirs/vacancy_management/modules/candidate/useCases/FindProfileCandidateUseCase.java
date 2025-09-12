package com.caiquepirs.vacancy_management.modules.candidate.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserNotFoundException;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.caiquepirs.vacancy_management.modules.candidate.mappers.CandidateMapper;
import com.caiquepirs.vacancy_management.modules.candidate.repositories.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindProfileCandidateUseCase {

    private final CandidateRepository repository;
    private final CandidateMapper mapper;

    public ProfileCandidateResponseDTO execute(UUID idCandidate){
        return repository.findById(idCandidate)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new UserNotFoundException("Candidate ID not found"));
    }
}
