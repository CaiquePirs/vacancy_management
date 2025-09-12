package com.caiquepirs.vacancy_management.modules.candidate.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserFoundException;
import com.caiquepirs.vacancy_management.exceptions.UserNotFoundException;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileUpdateCandidateRequestDTO;
import com.caiquepirs.vacancy_management.modules.candidate.entities.Candidate;
import com.caiquepirs.vacancy_management.modules.candidate.mappers.CandidateMapper;
import com.caiquepirs.vacancy_management.modules.candidate.repositories.CandidateRepository;
import com.caiquepirs.vacancy_management.modules.candidate.validator.ValidateCandidateField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateProfileCandidateUseCase {

    private final CandidateRepository repository;
    private final ValidateCandidateField validateCandidate;
    private final CandidateMapper candidateMapper;

    public ProfileCandidateResponseDTO execute(UUID id, ProfileUpdateCandidateRequestDTO profileDTO) {
        Candidate candidate = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Candidate ID not found"));

        if (profileDTO.username() != null || profileDTO.email() != null) {
            repository.findByUsernameOrEmail(profileDTO.username(), profileDTO.email())
                    .ifPresent(userFound -> {
                        if (!userFound.getId().equals(candidate.getId())) {
                            throw new UserFoundException("This username or email is already in use");
                        }
                    });
        }

        Candidate candidateUpdated = validateCandidate.validate(candidate, profileDTO);
        repository.save(candidateUpdated);
        return candidateMapper.toResponseDTO(candidateUpdated);
    }

}
