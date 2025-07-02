package com.caiquepirs.vacancy_management.modules.company.useCases;

import com.caiquepirs.vacancy_management.exceptions.DuplicateRecordException;
import com.caiquepirs.vacancy_management.exceptions.JobNotFoundException;
import com.caiquepirs.vacancy_management.exceptions.UserNotFoundException;
import com.caiquepirs.vacancy_management.modules.candidate.CandidateRepository;
import com.caiquepirs.vacancy_management.modules.company.enuns.JobStatus;
import com.caiquepirs.vacancy_management.modules.company.repositories.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateJobApplicationUseCase {

    private final JobRepository jobRepository;
    private final CandidateRepository candidateRepository;

    @Transactional
    public void execute(UUID candidateId, UUID jobId){
        var job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException("Job not found"));

        if(job.getStatus().equals(JobStatus.INACTIVE)){
            throw new JobNotFoundException("This job opening has been deactivated");
        }

        var candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        boolean alreadyApplied = job.getCandidates().stream()
                .anyMatch(c -> c.getId().equals(candidateId));

        if (alreadyApplied) {
            throw new DuplicateRecordException("User already registered for this vacancy");
        }

        job.getCandidates().add(candidate);
        candidate.getJobApplications().add(job);
        jobRepository.save(job);
    }
}
