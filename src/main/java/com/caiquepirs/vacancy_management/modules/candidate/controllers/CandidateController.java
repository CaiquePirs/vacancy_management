package com.caiquepirs.vacancy_management.modules.candidate.controllers;

import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileUpdateCandidateRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.useCases.CreateJobApplicationUseCase;
import com.caiquepirs.vacancy_management.modules.candidate.useCases.ProfileCandidateUseCase;
import com.caiquepirs.vacancy_management.modules.company.dto.ApplyJobResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@AllArgsConstructor
public class CandidateController {

    private final ProfileCandidateUseCase profileCandidateUseCase;
    private final CreateJobApplicationUseCase jobApplicationUseCase;

    @GetMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> getProfile(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");

        try {
            var profile = profileCandidateUseCase.getProfile(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> deleteProfile(HttpServletRequest request){
        var idCandidate = request.getAttribute("candidate_id");

        try {
             profileCandidateUseCase.deleteProfile(UUID.fromString(idCandidate.toString()));
             return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }

    @PutMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> updateProfile(HttpServletRequest request,
                                                @RequestBody @Valid ProfileUpdateCandidateRequestDTO profileDTO){
        var idCandidate = request.getAttribute("candidate_id").toString();

        try {
            var profileUpdated = profileCandidateUseCase.updateProfile(UUID.fromString(idCandidate), profileDTO);
            return ResponseEntity.ok(profileUpdated);

        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/jobs/{id}/appy")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> applyToJob(@PathVariable UUID id, HttpServletRequest request){
        var idCandidate = request.getAttribute("candidate_id").toString();

        try {
            jobApplicationUseCase.execute(UUID.fromString(idCandidate), id);
            return ResponseEntity.noContent().build();

        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/jobs/applications")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<List<ApplyJobResponseDTO>> getJobApplications(HttpServletRequest request) {
        String candidateIdStr = request.getAttribute("candidate_id").toString();

            UUID candidateId = UUID.fromString(candidateIdStr);
            List<ApplyJobResponseDTO> listJobs = profileCandidateUseCase.jobApplications(candidateId);

            return ResponseEntity.ok(listJobs);

    }
}
