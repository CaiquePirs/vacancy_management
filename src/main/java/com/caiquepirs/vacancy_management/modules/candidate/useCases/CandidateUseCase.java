package com.caiquepirs.vacancy_management.modules.candidate.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserFoundException;
import com.caiquepirs.vacancy_management.modules.candidate.CandidateEntity;
import com.caiquepirs.vacancy_management.modules.candidate.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CandidateUseCase {

    private final CandidateRepository repository;
    private final PasswordEncoder encoder;

    public CandidateEntity execute(CandidateEntity candidate) {
        repository.findByNameOrEmail(candidate.getUsername(), candidate.getEmail())
                .orElseThrow(() -> new UserFoundException("This User already exist"));

        candidate.setPassword(encoder.encode(candidate.getPassword()));
        return repository.save(candidate);
    }
}
