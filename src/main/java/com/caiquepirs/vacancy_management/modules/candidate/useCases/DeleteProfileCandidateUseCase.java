package com.caiquepirs.vacancy_management.modules.candidate.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserNotFoundException;
import com.caiquepirs.vacancy_management.modules.candidate.entities.Candidate;
import com.caiquepirs.vacancy_management.modules.candidate.repositories.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeleteProfileCandidateUseCase {

    private final CandidateRepository repository;

    public void execute(UUID id){
        Candidate candidate = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Candidate ID not found"));
        repository.delete(candidate);
    }
}
