package com.caiquepirs.vacancy_management.modules.job.useCases;

import com.caiquepirs.vacancy_management.exceptions.DuplicateRecordException;
import com.caiquepirs.vacancy_management.exceptions.JobNotFoundException;
import com.caiquepirs.vacancy_management.exceptions.UserNotFoundException;
import com.caiquepirs.vacancy_management.modules.candidate.entities.Candidate;
import com.caiquepirs.vacancy_management.modules.candidate.repositories.CandidateRepository;
import com.caiquepirs.vacancy_management.modules.job.entities.Job;
import com.caiquepirs.vacancy_management.modules.job.enums.JobStatus;
import com.caiquepirs.vacancy_management.modules.job.repositories.JobRepository;
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
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException("Job ID not found"));

        if(job.getStatus().equals(JobStatus.INACTIVE)){
            throw new JobNotFoundException("This job opening has been deactivated");
        }

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new UserNotFoundException("Candidate ID not found"));

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
