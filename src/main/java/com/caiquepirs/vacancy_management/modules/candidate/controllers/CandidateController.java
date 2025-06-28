package com.caiquepirs.vacancy_management.modules.candidate.controllers;

import com.caiquepirs.vacancy_management.modules.candidate.CandidateEntity;
import com.caiquepirs.vacancy_management.modules.candidate.useCases.CandidateUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
@AllArgsConstructor
public class CandidateController {

    private final CandidateUseCase useCase;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CandidateEntity candidate){
        try {
            var result =   useCase.execute(candidate);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
