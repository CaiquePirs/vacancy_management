package com.caiquepirs.vacancy_management.modules.candidate.controllers;

import com.caiquepirs.vacancy_management.docs.CandidateAuthApi;
import com.caiquepirs.vacancy_management.modules.candidate.dto.AuthCandidateResponseDTO;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.caiquepirs.vacancy_management.modules.candidate.entities.Candidate;
import com.caiquepirs.vacancy_management.modules.candidate.mappers.CandidateMapper;
import com.caiquepirs.vacancy_management.modules.candidate.dto.AuthCandidateRequestDTO;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateRequestDTO;
import com.caiquepirs.vacancy_management.modules.candidate.useCases.AuthCandidateUseCase;
import com.caiquepirs.vacancy_management.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidates/auth")
@AllArgsConstructor
public class AuthCandidateController implements CandidateAuthApi {

    private final AuthCandidateUseCase authCandidateUseCase;
    private final CandidateMapper candidateMapper;
    private final CreateCandidateUseCase CreateCandidateUseCase;

    @PostMapping("/login")
    public ResponseEntity<AuthCandidateResponseDTO> login(@RequestBody @Valid AuthCandidateRequestDTO authCandidateDTO) {
        var token = authCandidateUseCase.execute(authCandidateDTO);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<ProfileCandidateResponseDTO> register(@RequestBody @Valid ProfileCandidateRequestDTO candidateDTO){
            Candidate candidate = CreateCandidateUseCase.execute(candidateDTO);
            var candidateResponseDTO = candidateMapper.toResponseDTO(candidate);
            return ResponseEntity.status(HttpStatus.CREATED).body(candidateResponseDTO);
    }
}
