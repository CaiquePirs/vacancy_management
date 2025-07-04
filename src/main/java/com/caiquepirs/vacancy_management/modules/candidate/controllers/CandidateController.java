package com.caiquepirs.vacancy_management.modules.candidate.controllers;

import com.caiquepirs.vacancy_management.docs.CandidateControllerDoc;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileUpdateCandidateRequestDTO;
import com.caiquepirs.vacancy_management.modules.job.useCases.CreateJobApplicationUseCase;
import com.caiquepirs.vacancy_management.modules.candidate.useCases.ProfileCandidateUseCase;
import com.caiquepirs.vacancy_management.modules.job.dto.ApplyJobResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@AllArgsConstructor
public class CandidateController implements CandidateControllerDoc {

    private final ProfileCandidateUseCase profileCandidateUseCase;
    private final CreateJobApplicationUseCase jobApplicationUseCase;

    @GetMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<ProfileCandidateResponseDTO> getProfile(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");
        var profile = profileCandidateUseCase.getProfile(UUID.fromString(candidateId.toString()));
        return ResponseEntity.ok().body(profile);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Void> deleteProfile(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");
        profileCandidateUseCase.deleteProfile(UUID.fromString(candidateId.toString()));
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<ProfileCandidateResponseDTO> updateProfile(HttpServletRequest request,
                                                @RequestBody @Valid ProfileUpdateCandidateRequestDTO profileDTO) {
        var candidateId = request.getAttribute("candidate_id").toString();
        var profileUpdated = profileCandidateUseCase.updateProfile(UUID.fromString(candidateId), profileDTO);
        return ResponseEntity.ok(profileUpdated);
    }

    @PostMapping("/jobs/{id}/appy")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Void> applyToJob(@PathVariable UUID id, HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id").toString();
        jobApplicationUseCase.execute(UUID.fromString(candidateId), id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/jobs/applications")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<List<ApplyJobResponseDTO>> getJobApplications(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id").toString();
        List<ApplyJobResponseDTO> listJobs = profileCandidateUseCase.jobApplications(UUID.fromString(candidateId));
        return ResponseEntity.ok(listJobs);
    }
}
