package com.caiquepirs.vacancy_management.modules.candidate.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserFoundException;
import com.caiquepirs.vacancy_management.modules.candidate.CandidateMapper;
import com.caiquepirs.vacancy_management.modules.candidate.CandidateRepository;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileUpdateCandidateRequestDTO;
import com.caiquepirs.vacancy_management.modules.candidate.util.ValidateCandidateField;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProfileCandidateUseCase {

    private final CandidateRepository repository;
    private final CandidateMapper candidateMapper;
    private final ValidateCandidateField validateCandidate;

    public ProfileCandidateResponseDTO getProfile(UUID idCandidate){
        var candidate = repository.findById(idCandidate)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return candidateMapper.toResponseDTO(candidate);
    }

    public void deleteProfile(UUID id){
        var existProfile = repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        repository.delete(existProfile);
    }

    public ProfileCandidateResponseDTO updateProfile(UUID id, ProfileUpdateCandidateRequestDTO profileDTO) {
        var existProfile = repository.findById(id).map(candidate -> {

            if (profileDTO.username() != null || profileDTO.email() != null) {

                repository.findByUsernameOrEmail(profileDTO.username(), profileDTO.email())
                        .orElseThrow(() -> new UserFoundException("This user already exist"));
            }

            var candidateUpdated = validateCandidate.validate(candidate, profileDTO);
            return repository.save(candidateUpdated);

        }).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return candidateMapper.toResponseDTO(existProfile);

    }

}
