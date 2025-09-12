package com.caiquepirs.vacancy_management.modules.candidate.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserNotFoundException;
import com.caiquepirs.vacancy_management.modules.candidate.entities.Candidate;
import com.caiquepirs.vacancy_management.modules.candidate.repositories.CandidateRepository;
import com.caiquepirs.vacancy_management.modules.job.dto.ApplyJobResponseDTO;
import com.caiquepirs.vacancy_management.modules.job.entities.Job;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GetJobsApplicationCandidateUseCase {

    private final CandidateRepository repository;

    public List<ApplyJobResponseDTO> execute(UUID candidateId) {
        Candidate candidate = repository.findById(candidateId)
                .orElseThrow(() -> new UserNotFoundException("Candidate ID not found"));

        List<Job> jobs = candidate.getJobApplications();

        return jobs.stream()
                .map(job -> ApplyJobResponseDTO.builder()
                        .companyName(job.getCompany().getName())
                        .jobDescription(job.getDescription())
                        .jobLevel(job.getLevel())
                        .candidateName(candidate.getName())
                        .candidateEmail(candidate.getEmail())
                        .jobStatus(job.getStatus())
                        .build())
                .toList();
    }

}
