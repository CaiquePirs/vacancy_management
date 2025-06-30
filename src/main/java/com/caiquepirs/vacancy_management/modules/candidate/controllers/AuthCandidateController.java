package com.caiquepirs.vacancy_management.modules.candidate.controllers;

import com.caiquepirs.vacancy_management.modules.candidate.CandidateMapper;
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
@RequestMapping("/candidate/auth")
@AllArgsConstructor
public class AuthCandidateController {

    private final AuthCandidateUseCase authCandidateUseCase;
    private final CandidateMapper candidateMapper;
    private final CreateCandidateUseCase CreateCandidateUseCase;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthCandidateRequestDTO authCandidateDTO){
        try{
            var token = authCandidateUseCase.execute(authCandidateDTO);
            return ResponseEntity.ok(token);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid ProfileCandidateRequestDTO candidateDTO){
        try {
            var candidateEntity = CreateCandidateUseCase.execute(candidateDTO);
            var candidateResponseDTO = candidateMapper.toResponseDTO(candidateEntity);

            return ResponseEntity.status(HttpStatus.CREATED).body(candidateResponseDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
