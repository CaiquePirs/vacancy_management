package com.caiquepirs.vacancy_management.modules.candidate.controllers;

import com.caiquepirs.vacancy_management.modules.candidate.CandidateMapper;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateRequestDTO;
import com.caiquepirs.vacancy_management.modules.candidate.useCases.CreateCandidateUseCase;
import com.caiquepirs.vacancy_management.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@AllArgsConstructor
public class CandidateController {

    private final CreateCandidateUseCase useCase;
    private final ProfileCandidateUseCase profileCandidateUseCase;
    private final CandidateMapper candidateMapper;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid ProfileCandidateRequestDTO candidateDTO){
        try {
            var candidateEntity = useCase.execute(candidateDTO);
            var candidateResponseDTO = candidateMapper.toResponseDTO(candidateEntity);

            return ResponseEntity.status(HttpStatus.CREATED).body(candidateResponseDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<Object> getProfile(UUID id, HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");

        try {
            var profile = profileCandidateUseCase.getProfile(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
