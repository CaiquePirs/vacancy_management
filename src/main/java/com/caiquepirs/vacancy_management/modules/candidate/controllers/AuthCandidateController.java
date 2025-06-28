package com.caiquepirs.vacancy_management.modules.candidate.controllers;

import com.caiquepirs.vacancy_management.modules.candidate.dto.AuthCandidateRequestDTO;
import com.caiquepirs.vacancy_management.modules.candidate.useCases.AuthCandidateUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
@AllArgsConstructor
public class AuthCandidateController {

    private final AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody @Valid AuthCandidateRequestDTO authCandidateDTO){
        try{
            var token = authCandidateUseCase.execute(authCandidateDTO);
            return ResponseEntity.ok(token);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }
}
