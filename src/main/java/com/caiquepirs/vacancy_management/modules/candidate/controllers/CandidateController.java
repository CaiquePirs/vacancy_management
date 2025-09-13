package com.caiquepirs.vacancy_management.modules.candidate.controllers;

import com.caiquepirs.vacancy_management.docs.CandidateApi;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileUpdateCandidateRequestDTO;
import com.caiquepirs.vacancy_management.modules.candidate.useCases.DeleteProfileCandidateUseCase;
import com.caiquepirs.vacancy_management.modules.candidate.useCases.FindProfileCandidateUseCase;
import com.caiquepirs.vacancy_management.modules.candidate.useCases.GetJobsApplicationCandidateUseCase;
import com.caiquepirs.vacancy_management.modules.candidate.useCases.UpdateProfileCandidateUseCase;
import com.caiquepirs.vacancy_management.modules.job.useCases.CreateJobApplicationUseCase;
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
@RequestMapping("/candidates")
@AllArgsConstructor
public class CandidateController implements CandidateApi {

    private final CreateJobApplicationUseCase jobApplicationUseCase;
    private final FindProfileCandidateUseCase findProfileCandidateUseCase;
    private final UpdateProfileCandidateUseCase updateProfileCandidateUseCase;
    private final DeleteProfileCandidateUseCase deleteProfileCandidateUseCase;
    private final GetJobsApplicationCandidateUseCase getJobsApplicationCandidateUseCase;

    @GetMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<ProfileCandidateResponseDTO> getProfile(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");
        var profile = findProfileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
        return ResponseEntity.ok().body(profile);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Void> deleteProfile(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");
        deleteProfileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<ProfileCandidateResponseDTO> updateProfile(HttpServletRequest request,
                                                @RequestBody @Valid ProfileUpdateCandidateRequestDTO profileDTO) {
        var candidateId = request.getAttribute("candidate_id").toString();
        var profileUpdated = updateProfileCandidateUseCase.execute(UUID.fromString(candidateId), profileDTO);
        return ResponseEntity.ok(profileUpdated);
    }

    @PostMapping("/jobs/{jobId}/appy")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Void> applyToJob(@PathVariable UUID jobId, HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id").toString();
        jobApplicationUseCase.execute(UUID.fromString(candidateId), jobId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/jobs/applications")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<List<ApplyJobResponseDTO>> getJobApplications(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id").toString();
        var listJobs = getJobsApplicationCandidateUseCase.execute(UUID.fromString(candidateId));
        return ResponseEntity.ok(listJobs);
    }
}
