package com.caiquepirs.vacancy_management.modules.candidate.useCases;

import com.caiquepirs.vacancy_management.modules.candidate.CandidateMapper;
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
    private final CandidateMapper candidateMapper;

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

}
