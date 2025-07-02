package com.caiquepirs.vacancy_management.modules.candidate.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserFoundException;
import com.caiquepirs.vacancy_management.modules.candidate.entities.CandidateEntity;
import com.caiquepirs.vacancy_management.modules.candidate.mappers.CandidateMapper;
import com.caiquepirs.vacancy_management.modules.candidate.repositories.CandidateRepository;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCandidateUseCase {

    private final CandidateRepository repository;
    private final PasswordEncoder encoder;
    private final CandidateMapper candidateMapper;

    public CandidateEntity execute(ProfileCandidateRequestDTO candidateDTO) {
        repository.findByUsernameOrEmail(candidateDTO.getUsername(), candidateDTO.getEmail())
                .ifPresent(candidateEntity -> {
                   throw  new UserFoundException("This User already exist");
                });

        var passwordEncoder = encoder.encode(candidateDTO.getPassword());
        var candidate = candidateMapper.toEntity(candidateDTO);
        candidate.setPassword(passwordEncoder);

        return repository.save(candidate);
    }
}
