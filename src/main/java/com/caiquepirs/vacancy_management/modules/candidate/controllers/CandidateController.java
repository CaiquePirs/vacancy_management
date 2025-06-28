package com.caiquepirs.vacancy_management.modules.candidate.controllers;

import com.caiquepirs.vacancy_management.modules.candidate.CandidateEntity;
import com.caiquepirs.vacancy_management.modules.candidate.useCases.CandidateUseCase;
import com.caiquepirs.vacancy_management.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@AllArgsConstructor
public class CandidateController {

    private final CandidateUseCase useCase;
    private final ProfileCandidateUseCase profileCandidateUseCase;

    @PostMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> create(@RequestBody @Valid CandidateEntity candidate){
        try {
            var result =   useCase.execute(candidate);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    @GetMapping
    public ResponseEntity<Object> get(UUID id, HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");

        try {
            var profile = profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
